/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()

        mavenCentral {
            content {
                includeGroup("org.jetbrains.kotlinx")
            }
        }
    }
}

include(":socket-io")
include(":sample:android-app")
include(":sample:mpp-library")