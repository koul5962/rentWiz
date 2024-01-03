plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.rentwiz.app.coreui"
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
    buildFeatures {
        viewBinding = true
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

    //Test
    testImplementation(Dependencies.AndroidX.Test.AndroidTest.junit)
    androidTestImplementation(Dependencies.AndroidX.Test.AndroidTest.androidx_test_ext_junit)
    androidTestImplementation(Dependencies.AndroidX.Test.Espresso.core)
}