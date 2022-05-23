/*
 * Copyright 2022 Reach2027
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.reach.uilayer"

    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = Versions.JAVA
        targetCompatibility = Versions.JAVA
    }

    kotlinOptions {
        jvmTarget = Versions.JVM
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
}

dependencies {
    implementation(project(":domainlayer"))

    implementation(Deps.COROUTINE_ANDROID)
    testImplementation(Deps.TEST_COROUTINE)

    implementation(Deps.HILT_ANDROID)
    kapt(Deps.HILT_COMPILER)

    implementation(Deps.NAVIGATION)

    implementation(Deps.HILT_NAVIGATION_COMPOSE)

    implementation(Deps.CORE)

    implementation(Deps.ACTIVITY_COMPOSE)

    // Compose
    implementation(Deps.COMPOSE_COMPILER)
    implementation(Deps.COMPOSE_ANIMATION)
    implementation(Deps.COMPOSE_MATERIAL)
    implementation(Deps.COMPOSE_MATERIAL_ICON)
    implementation(Deps.COMPOSE_FOUNDATION)
    implementation(Deps.COMPOSE_UI)
    implementation(Deps.COMPOSE_UI_TOOL)
    debugImplementation(Deps.COMPOSE_UI_TOOL)
    implementation(Deps.COMPOSE_RUNTIME)

    implementation(Deps.ACC_NAV_ANIMATION)

    // Lifecycle
    implementation(Deps.LIFECYCLE_COMMON)
    implementation(Deps.LIFECYCLE_RUNTIME)
    implementation(Deps.VIEW_MODEL)
    implementation(Deps.VIEW_MODEL_COMPOSE)
    implementation(Deps.VIEW_MODEL_SAVED_STATE)

    // Coil
    implementation(Deps.COIL)
    implementation(Deps.COIL_COMPOSE)
}
