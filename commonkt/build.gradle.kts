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
    id("java-library")
    kotlin("jvm")
    kotlin("kapt")
    id("com.google.devtools.ksp")
}

java {
    sourceCompatibility = Versions.JAVA
    targetCompatibility = Versions.JAVA
}

dependencies {
    implementation(Deps.COROUTINE_CORE)
    testImplementation(Deps.TEST_COROUTINE)

    implementation(Deps.HILT_CORE)
    kapt(Deps.HILT_COMPILER)

    implementation(Deps.RETROFIT2)
    implementation(Deps.RETROFIT2_MOSHI)

    implementation(Deps.MOSHI)
    ksp(Deps.MOSHI_CODEGEN)
}
