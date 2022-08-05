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

import org.gradle.api.JavaVersion

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

object Versions {

    // Lang
    val JAVA = JavaVersion.VERSION_1_8

    const val JVM = "1.8"

    // Plugins
    const val AGP = "7.2.1"

    const val KOTLIN = "1.7.0"

    const val KSP = "1.7.0-1.0.6"

    const val SPOTLESS = "6.9.0"
    const val KT_LINT = "0.46.1"

    // SDK
    const val COMPILE_SDK = 32
    const val MIN_SDK = 23
    const val TARGET_SDK = 32

    const val VERSION_CODE = 1
    const val VERSION_NAME = "0.0.1"

    // JVM Dependencies
    const val COROUTINE = "1.6.4"
    const val RETROFIT2 = "2.9.0"
    const val MOSHI = "1.13.0"

    // Android Dependencies
    const val COLLECTION = "1.2.0"
    const val CORE = "1.8.0"
    const val ACTIVITY_COMPOSE = "1.5.1"
    const val COMPOSE = "1.2.0"
    const val ACCOMPANIST = "0.25.0"
    const val LIFECYCLE = "2.5.1"
    const val NAVIGATION = "2.4.2"
    const val HILT = "2.43.2"
    const val HILT_NAVIGATION_COMPOSE = "1.0.0"
    const val ROOM = "2.4.3"
    const val COIL = "2.1.0"

    // Test
    const val JUNIT = "4.13.2"

    const val TEST_CORE = "1.4.0"
    const val TEST_RUNNER = "1.4.0"
    const val TEST_RULES = "1.4.0"
    const val TEST_EXT_JUNIT = "1.1.3"

    const val ROBOLECTRIC = "4.7.3"
    const val UI_AUTOMATOR = "2.2.0"
}
