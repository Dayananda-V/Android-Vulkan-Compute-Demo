cmake_minimum_required(VERSION 3.4.1)

add_library(vulkan_compute_demo SHARED
            VulkanComputeDemoJni.cpp)

target_link_libraries(vulkan_compute_demo
                      android
                      vulkan
                      log)