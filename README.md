# Android Vulkan Compute Demo

Examples of using the Khronos Group's Vulkan Compute API standard to perform vector addition on Android Phone Operating System.

You will need [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), [Android SDK](https://developer.android.com/studio/index.html), [Android NDK](https://developer.android.com/ndk) and an Android device to use this.

## Build and Installation
### Build APK

We use [Gradle](https://gradle.org) to build. Please follow [the installation instruction](https://gradle.org/install) for your operating system.

Run following script to generate the apk file.

```bash
$ export ANDROID_HOME=[Path to your Android SDK, e.g., ~/Android/sdk]
$ export ANDROID_NDK_HOME=[Path to your Android NDK, e.g., ~/Android/sdk/ndk-bundle]
$ gradle clean build
```
### Install APK
In `app/build/outputs/apk/debug` you'll find `app-debug.apk`. Upload `app-debug.apk` to your Android device and install it.

```bash
$ adb install -r /app/build/outputs/apk/debug/app-debug.apk
Success
```