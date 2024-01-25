import Config.defaultFlavors
import Config.getFlavorList
import Config.getKeys
import Config.getVariantFields
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.parcelize")
    kotlin("kapt")
    //id("com.google.devtools.ksp")
    id(BuildPlugins.hilt)
    id(BuildPlugins.configDroidPlugin)
}

configdroid {
    className = "BuildConfigKeys"
    packageName = "com.rentwiz.app"
    access = "public"
}

getKeys().forEach {
    configdroid {
        prop(it, it)
    }
}

android {
    namespace = "com.rentwiz.app"
    compileSdk = Dependencies.Versions.compile_sdk

    defaultConfig {
        applicationId = "com.rentwiz.app"
        minSdk = Dependencies.Versions.min_sdk
        targetSdk = Dependencies.Versions.target_sdk
        versionCode = Dependencies.Versions.app_version_code
        versionName = Dependencies.Versions.app_version_name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        if(rootProject.file(SigningProperties.releasePropertiesFilePath).exists()) {
            val releaseProperties = Properties()
            releaseProperties.load(
                FileInputStream(rootProject.file(SigningProperties.releasePropertiesFilePath))
            )
            create("releaseConfig") {
                storeFile = file(releaseProperties.getProperty(SigningProperties.storeFile))
                storePassword = releaseProperties.getProperty(SigningProperties.storePassword)
                keyAlias = releaseProperties.getProperty(SigningProperties.keyAlias)
                keyPassword = releaseProperties.getProperty(SigningProperties.keyPassword)
            }
        }
        if(rootProject.file(SigningProperties.debugPropertiesFilePath).exists()) {
            val debugProperties = Properties()
            debugProperties.load(
                FileInputStream(rootProject.file(SigningProperties.debugPropertiesFilePath))
            )
            create("debugConfig") {
                storeFile = file(debugProperties.getProperty(SigningProperties.storeFile))
                storePassword = debugProperties.getProperty(SigningProperties.storePassword)
                keyAlias = debugProperties.getProperty(SigningProperties.keyAlias)
                keyPassword = debugProperties.getProperty(SigningProperties.keyPassword)
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            if (signingConfigs.names.contains("releaseConfig")) {
                signingConfig = signingConfigs.getByName("releaseConfig")
            }
        }
        getByName("debug") {
            if (signingConfigs.names.contains("debugConfig")) {
                signingConfig = signingConfigs.getByName("debugConfig")
            }
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    applicationVariants.all {
        if(buildType.name == "release") {
            buildConfigField("java.util.Map<String,String>", "RELEASE_MAP", getVariantFields(flavorName))
            defaultFlavors.forEach { value ->
                buildConfigField(
                    "java.util.Map<String,String>",
                    "${value.uppercase()}_MAP",
                    "null"
                )
            }
            buildConfigField("java.util.ArrayList<String>", "SET_OF_FLAVORS", "null")
        } else {
            buildConfigField("java.util.Map<String,String>", "RELEASE_MAP", "null")
            defaultFlavors.forEach { value ->
                buildConfigField(
                    "java.util.Map<String,String>",
                    "${value.uppercase()}_MAP",
                    getVariantFields(value)
                )
            }
            buildConfigField("java.util.ArrayList<String>", "SET_OF_FLAVORS", getFlavorList())

        }
    }

    flavorDimensions += "flavor"
    productFlavors {
        create("qa") {
            versionNameSuffix = "-qa"
        }
        create("prod") {
            versionNameSuffix = ""
        }
        create("dev") {
            versionNameSuffix = "-dev"
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(project(":core-ui"))
    implementation(project(":core"))

    //AndroidX
    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.app_compat)
    implementation(Dependencies.AndroidX.constraint_layout)
    implementation(Dependencies.AndroidX.navigation_fragment)
    implementation(Dependencies.AndroidX.navigation_ui)
    implementation(Dependencies.AndroidX.recylerview)

    implementation(Dependencies.material_components)

    //RxJava
    implementation(Dependencies.RxJava.main)
    implementation(Dependencies.RxJava.android)

    //Retrofit
    implementation(Dependencies.Retrofit.main)
    implementation(Dependencies.Retrofit.rxjava)
    implementation(Dependencies.Retrofit.moshi)
    implementation(Dependencies.Retrofit.gson)

    //Moshi
    implementation(Dependencies.Moshi.kotlin)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    kapt(Dependencies.Moshi.codegen)

    //Hilt
    implementation(Dependencies.Hilt.hilt)
    kapt(Dependencies.Hilt.hilt_compiler)

    //Compose
    implementation(platform(Dependencies.AndroidX.Compose.compose_bom))
    androidTestImplementation(Dependencies.AndroidX.Compose.compose_bom)
    //Compose Material Design 3
    implementation(Dependencies.AndroidX.Compose.compose_material)
    //Compose Integration with activities
    implementation(Dependencies.AndroidX.Compose.compose_activity)
    //Compose Preview Support
    implementation(Dependencies.AndroidX.Compose.compose_ui_tooling_preview)
    debugImplementation(Dependencies.AndroidX.Compose.compose_ui_tooling)
    //Compose Coil
    implementation(Dependencies.AndroidX.Compose.compose_coil)
    //
    implementation(Dependencies.AndroidX.Compose.compose_navigation)

    //Room
    //implementation(Dependencies.Room.room_runtime)
    //kapt(Dependencies.Room.room_compiler)

    implementation(Dependencies.Room.room_runtime)
   /* implementation(Dependencies.Room.room_ktx)
    kapt(Dependencies.Room.room_compiler)*/

    //Test
    testImplementation(Dependencies.AndroidX.Test.AndroidTest.junit)
    androidTestImplementation(Dependencies.AndroidX.Test.AndroidTest.androidx_test_ext_junit)
    androidTestImplementation(Dependencies.AndroidX.Test.Espresso.core)

}