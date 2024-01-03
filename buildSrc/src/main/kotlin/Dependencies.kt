object Dependencies {
    object Versions {
        const val compile_sdk = 34
        const val min_sdk = 24
        const val target_sdk = 34
        const val app_version_code = 1
        const val app_version_name = "1.0"

        const val android_plugin = "8.1.4"
        const val kotlin = "1.9.20"
        const val hilt = "2.49"
        const val mb14_configdroid = "1.1.0"
        const val androidx_core = "1.12.0"
        const val appcompat = "1.6.1"
        const val material_components = "1.11.0"
        const val constraintlayout = "2.1.4"
        const val nav_version = "2.7.6"
        const val rxjava = "3.1.5"
        const val rxandroid = "3.0.2"
        const val retrofit = "2.9.0"
        const val moshi = "1.15.0"
        const val recylerview = "1.3.2"
        const val composeBom = "2023.10.01"
        const val android_test_junit = "4.13.2"
        const val test_ext_junit = "1.1.5"
        const val espresso = "3.5.1"
        const val chucker = "4.0.0"
        const val compose = "1.1.1"
    }

    object BuildClassPathPlugins {
        const val android_plugin = "com.android.tools.build:gradle:${Versions.android_plugin}"
        const val kotlin_gradle_plugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val mb14_configdroid_plugin = "com.mb14:configdroid:${Versions.mb14_configdroid}"
        const val hilt_android_gradle_plugin =
            "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:${Versions.androidx_core}"
        const val app_compat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val constraint_layout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
        const val navigation_fragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
        const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
        const val recylerview = "androidx.recyclerview:recyclerview:${Versions.recylerview}"

        object Compose {
            val compose_bom = "androidx.compose:compose-bom:${Versions.composeBom}"
            val compose_material = "androidx.compose.material3:material3:${Versions.compose}"
            val compose_ui_tooling_preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
            val compose_ui_tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        }

        object Test {
            object AndroidTest {
                const val junit = "junit:junit:${Versions.android_test_junit}"
                const val androidx_test_ext_junit= "androidx.test.ext:junit:${Versions.test_ext_junit}"
            }

            object Espresso {
                const val core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
            }
        }
    }

    object Hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hilt_compiler =
            "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }

    object RxJava {
        const val main = "io.reactivex.rxjava3:rxjava:${Versions.rxjava}"
        const val android = "io.reactivex.rxjava3:rxandroid:${Versions.rxandroid}"
    }

    object Retrofit {
        const val main = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val rxjava = "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit}"
        const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object Moshi {
        const val kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    }

    object Chucker {
        const val library = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
        const val library_no_op = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"
    }
    const val material_components =
        "com.google.android.material:material:${Versions.material_components}"
}

object BuildPlugins {
    const val configDroidPlugin = "com.mb14.configdroid"
    const val hilt = "com.google.dagger.hilt.android"
}

object SigningProperties {
    const val releasePropertiesFilePath = "config/release.properties"
    const val debugPropertiesFilePath = "config/debug.properties"
    const val storeFile = "storeFile"
    const val storePassword = "storePassword"
    const val keyAlias = "keyAlias"
    const val keyPassword = "keyPassword"
}