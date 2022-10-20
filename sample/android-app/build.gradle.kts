/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {

    compileSdk = 31
    dexOptions {
        javaMaxHeapSize = "2g"
    }

    defaultConfig {

        minSdk = 28
        targetSdk = 31
        applicationId = "dev.icerock.moko.samples.socketio"

        versionCode = 1
        versionName = "0.2.0"

        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            viewBinding
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            viewBinding
        }
    }
    packagingOptions {
        resources {
            excludes += setOf("META-INF/*.kotlin_module")
        }
    }

    namespace = "com.icerockdev"
}

dependencies {

    implementation(libs.appCompat)

    implementation(projects.sample.mppLibrary)
    implementation(project(mapOf("path" to ":sample:mpp-library")))
    implementation(project(mapOf("path" to ":sample:mpp-library")))
    implementation(project(mapOf("path" to ":sample:mpp-library")))
    implementation(project(mapOf("path" to ":sample:mpp-library")))
    implementation("com.google.android.material:material:1.6.1")
}
