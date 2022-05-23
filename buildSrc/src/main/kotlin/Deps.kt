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

object Deps {

    // JVM
    const val COROUTINE_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE}"
    const val TEST_COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINE}"

    const val HILT_CORE = "com.google.dagger:hilt-core:${Versions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${Versions.HILT}"

    const val RETROFIT2 = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT2}"
    const val RETROFIT2_MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT2}"

    const val MOSHI = "com.squareup.moshi:moshi:${Versions.MOSHI}"
    const val MOSHI_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.MOSHI}"

    // Android
    const val COLLECTION = "androidx.collection:collection-ktx:${Versions.COLLECTION}"

    const val COROUTINE_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE}"

    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"

    const val NAVIGATION = "androidx.navigation:navigation-compose:${Versions.NAVIGATION}"

    const val HILT_NAVIGATION_COMPOSE =
        "androidx.hilt:hilt-navigation-compose:${Versions.HILT_NAVIGATION_COMPOSE}"

    const val CORE = "androidx.core:core-ktx:${Versions.CORE}"

    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE}"

    const val COMPOSE_COMPILER = "androidx.compose.compiler:compiler:${Versions.COMPOSE}"
    const val COMPOSE_ANIMATION = "androidx.compose.animation:animation:${Versions.COMPOSE}"

    // Material
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE}"
    const val COMPOSE_MATERIAL3 = "androidx.compose.material3:material3:1.0.0-alpha09"
    const val COMPOSE_MATERIAL_ICON =
        "androidx.compose.material:material-icons-extended:${Versions.COMPOSE}"

    // Foundation
    const val COMPOSE_FOUNDATION = "androidx.compose.foundation:foundation:${Versions.COMPOSE}"

    // UI
    const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE}"
    const val COMPOSE_UI_TOOL = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"

    const val COMPOSE_TEST = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"

    // Runtime
    const val COMPOSE_RUNTIME = "androidx.compose.runtime:runtime:${Versions.COMPOSE}"

    const val ACC_NAV_ANIMATION =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.ACCOMPANIST}"

    const val LIFECYCLE_COMMON = "androidx.lifecycle:lifecycle-common-java8:${Versions.LIFECYCLE}"
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
    const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    const val VIEW_MODEL_COMPOSE =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.LIFECYCLE}"
    const val VIEW_MODEL_SAVED_STATE =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.LIFECYCLE}"

    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM = "androidx.room:room-ktx:${Versions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    const val TEST_ROOM = "androidx.room:room-testing:${Versions.ROOM}"

    const val COIL = "io.coil-kt:coil:${Versions.COIL}"
    const val COIL_COMPOSE = "io.coil-kt:coil-compose:${Versions.COIL}"

    const val JUNIT = "junit:junit:${Versions.JUNIT}"

    const val TEST_CORE = "androidx.test:core-ktx:${Versions.TEST_CORE}"
    const val TEST_RUNNER = "androidx.test:runner:${Versions.TEST_RUNNER}"
    const val TEST_RULES = "androidx.test:rules:${Versions.TEST_RULES}"
    const val TEST_EXT_JUNIT = "androidx.test.ext:junit-ktx:${Versions.TEST_EXT_JUNIT}"

    const val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.ROBOLECTRIC}"

    const val UI_AUTOMATOR = "androidx.test.uiautomator:uiautomator:${Versions.UI_AUTOMATOR}"
}
