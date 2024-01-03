plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id(BuildPlugins.hilt)
}

android {
    namespace = "com.rentwiz.app.core"
    compileSdk = Dependencies.Versions.compile_sdk

    defaultConfig {
        minSdk = Dependencies.Versions.min_sdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    //AndroidX
    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.app_compat)
    
    implementation(Dependencies.material_components)

    //Hilt
    implementation(Dependencies.Hilt.hilt)
    kapt(Dependencies.Hilt.hilt_compiler)

    //Retrofit
    implementation(Dependencies.Retrofit.main)
    implementation(Dependencies.Retrofit.rxjava)
    implementation(Dependencies.Retrofit.moshi)
    implementation(Dependencies.Retrofit.gson)

    //Chucker
    debugImplementation(Dependencies.Chucker.library)
    releaseImplementation(Dependencies.Chucker.library_no_op)

    //Test
    testImplementation(Dependencies.AndroidX.Test.AndroidTest.junit)
    androidTestImplementation(Dependencies.AndroidX.Test.AndroidTest.androidx_test_ext_junit)
    androidTestImplementation(Dependencies.AndroidX.Test.Espresso.core)
}