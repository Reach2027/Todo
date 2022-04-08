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
    id("com.android.application") version Versions.AGP apply false
    id("com.android.library") version Versions.AGP apply false
    id("org.jetbrains.kotlin.android") version Versions.KOTLIN apply false

    id("com.google.devtools.ksp") version Versions.KSP

    id("com.diffplug.spotless") version Versions.SPOTLESS
}

buildscript {

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}")
    }

}

apply(plugin = "com.diffplug.spotless")
spotless {
    kotlin {
        target("**/*.kt")
        ktlint(Versions.KT_LINT)
            .userData(mapOf("android" to "true"))
        licenseHeaderFile(
            rootProject.file("spotless/copyright"),
            "package|object|import|interface"
        )
    }

    kotlinGradle {
        target("**/*.gradle.kts")
        ktlint(Versions.KT_LINT)
        licenseHeaderFile(
            rootProject.file("spotless/copyright"),
            "package|import|tasks|apply|plugins|include|val|object"
        )
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
