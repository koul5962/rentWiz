// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath(Dependencies.BuildClassPathPlugins.android_plugin)
        classpath(Dependencies.BuildClassPathPlugins.kotlin_gradle_plugin)
        classpath(Dependencies.BuildClassPathPlugins.hilt_android_gradle_plugin)
        classpath(Dependencies.BuildClassPathPlugins.mb14_configdroid_plugin)
    }
}

