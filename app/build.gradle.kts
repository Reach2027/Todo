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
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        applicationId = "com.reach.todo"
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val release by getting {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(Deps.CORE)

    implementation(Deps.ACTIVITY_COMPOSE)

    // coroutine
    implementation(Deps.COROUTINE)

    testImplementation(Deps.TEST_COROUTINE)

    // compose
    implementation(Deps.COMPOSE_ANIMATION)

    implementation(Deps.COMPOSE_COMPILER)

    implementation(Deps.COMPOSE_FOUNDATION)

    implementation(Deps.COMPOSE_MATERIAL)
    implementation(Deps.COMPOSE_MATERIAL_ICON)

    implementation(Deps.COMPOSE_RUNTIME)

    implementation(Deps.COMPOSE_UI)
    implementation(Deps.COMPOSE_UI_TOOL)
    debugImplementation(Deps.DEBUG_COMPOSE_UI)

    // lifecycle
    implementation(Deps.LIFECYCLE_RUNTIME)
    implementation(Deps.VIEW_MODEL)
    implementation(Deps.VIEW_MODEL_COMPOSE)
    implementation(Deps.VIEW_MODEL_SAVED_STATE)

    // navigation
    implementation(Deps.NAVIGATION)

    // hilt
    implementation(Deps.HILT)
    kapt(Deps.HILT_COMPILER)

    implementation(Deps.HILT_NAVIGATION)

    // room
    implementation(Deps.ROOM)
    implementation(Deps.ROOM_RUNTIME)
    ksp(Deps.ROOM_COMPILER)

    testImplementation(Deps.TEST_ROOM)

    // coil
    implementation(Deps.COIL)
    implementation(Deps.COIL_COMPOSE)

    // retrofit2
    implementation(Deps.RETROFIT2)
    implementation(Deps.RETROFIT2_MOSHI)

    // moshi
    implementation(Deps.MOSHI)
    ksp(Deps.MOSHI_CODEGEN)

    testImplementation(Deps.JUNIT)

    testImplementation(Deps.TEST_CORE)
    testImplementation(Deps.TEST_JUNIT)

    testImplementation(Deps.ROBOLECTRIC)
}
