/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("dev.icerock.mobile.multiplatform.cocoapods")
    id("dev.icerock.mobile.multiplatform.android-manifest")
    id("dev.icerock.mobile.multiplatform.ios-framework")
}

kotlin {
    android()
    ios()
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
    implementation(project(mapOf("path" to ":socket-io")))
    implementation(project(mapOf("path" to ":socket-io")))
    implementation(project(mapOf("path" to ":socket-io")))
    implementation(project(mapOf("path" to ":socket-io")))
    implementation(project(mapOf("path" to ":socket-io")))
    implementation("org.apache.commons:commons-text:1.10.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.github.pgreze:kotlin-process:1.4")
    implementation("com.jaredrummler:ktsh:1.0.0")
    commonMainImplementation(libs.serialization)
    commonMainApi(projects.socketIo)
}
android {
    namespace = "com.icerockdev.library"
    compileSdk = 28
    buildToolsVersion = "28.0.3"
}

framework {
    export(projects.socketIo)
}

cocoaPods {
    pod("mokoSocketIo", onlyLink = true)
}
