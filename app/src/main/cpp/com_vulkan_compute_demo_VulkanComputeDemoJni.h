#include <jni.h>
#include <time.h>
#include <vulkan/vulkan.h>


#ifndef _Included_com_vulkan_compute_demo_VulkanComputeDemoJni
#define _Included_com_vulkan_compute_demo_VulkanComputeDemoJni
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_vulkan_compute_demo_VulkanComputeDemoJni
 * Method:    nativeVulkanComputeJni
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL
Java_com_vulkan_compute_demo_MainActivity_nativeVulkanComputeDemoJni( JNIEnv* env,
                                                                      jobject thiz,
                                                                      jbyteArray spvByteArray);

VkResult vulkanVecorAdditionComputePerform(int spvArraySize, char* spvArray);

static double now_ms(void) {
    struct timespec res;
    clock_gettime(CLOCK_REALTIME, &res);
    return 1000.0 * res.tv_sec + (double) res.tv_nsec / 1e6;
}

#ifdef __cplusplus
}
#endif
#endif