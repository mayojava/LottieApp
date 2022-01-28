package com.example.lottieapp.buildsrc

object Libs {
    object Accompanist {
        const val version = "0.24.0-alpha"

        const val insets = "com.google.accompanist:accompanist-insets:$version"
        const val placeholder = "com.google.accompanist:accompanist-placeholder:$version"
        const val systemui = "com.google.accompanist:accompanist-systemuicontroller:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val navigation = "androidx.navigation:navigation-compose:2.5.0-alpha01"

        object Compose {
            const val version = "1.2.0-alpha02"

            const val animation = "androidx.compose.animation:animation:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val iconsExtended = "androidx.compose.material:material-icons-extended:$version"
            const val material = "androidx.compose.material:material:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val runtime_livedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val tooling_preview = "androidx.compose.ui:ui-tooling-preview:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val uiUtil = "androidx.compose.ui:ui-util:$version"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
        }

        object Activity {
            const val version = "1.4.0"

            const val activity_compose = "androidx.activity:activity-compose:$version"
            const val activity_ktx = "androidx.activity:activity-ktx:$version"
        }

        object Lifecycle {
            const val version = "2.4.0"

            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val viewmodel_savedstate = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
            const val runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewmodel_compose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
        }
    }

    object Coroutines {
        private const val version = "1.6.0"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:1.4.0"
        const val coilGif = "io.coil-kt:coil-gif:1.4.0"
    }
}