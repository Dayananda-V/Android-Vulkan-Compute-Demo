/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vulkan.compute.demo;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    static {
        System.loadLibrary("vulkan_compute_demo");
    }

    public native String  nativeVulkanComputeDemoJni(byte[] spvByteContent);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_jni);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] spvByteContent = getBytesFromFile(MainActivity.this.getAssets(),
                            "vector_add.spv");
                    final String resultMsg = nativeVulkanComputeDemoJni(spvByteContent);
                    //the code you want to run on main thread
                    MainActivity.this.runOnUiThread(new Runnable() {

                        public void run() {
                            TextView tv = (TextView)findViewById(R.id.hello_textview);
                            tv.setText( resultMsg );
                        }
                    });
                } catch (IOException ex) {
                    Log.e(TAG, "Fail to load asset file, reason is " + ex);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        TextView tv = (TextView)findViewById(R.id.hello_textview);
        tv.setText( "" );
        super.onPause();
    }

    private byte[] getBytesFromFile(AssetManager assets, String fileName) throws IOException {
        InputStream is = assets.open(fileName);
        int length = is.available();
        byte[] bytes = new byte[length];
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        try {
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
        } finally {
            is.close();
        }
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + fileName);
        }
        return bytes;
    }
}