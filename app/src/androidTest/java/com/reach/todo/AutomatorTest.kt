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

package com.reach.todo

import android.app.Application
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * 2022/5/12  Reach
 */
@RunWith(AndroidJUnit4::class)
class AutomatorTest {

    private val todoPkg = "com.reach.todo"

    private val timeout = 5000L

    private lateinit var device: UiDevice

    @Before
    fun startTodoActivity() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        device.pressHome()

        val launcherPkg: String = device.launcherPackageName
        assertThat(launcherPkg, notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPkg).depth(0)),
            timeout
        )

        val context = ApplicationProvider.getApplicationContext<Application>()
        val intent = context.packageManager.getLaunchIntentForPackage(todoPkg)
            ?.apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
        context.startActivity(intent)
        device.wait(
            Until.hasObject(By.pkg(todoPkg).depth(0)),
            timeout
        )
    }

    @Test
    fun addTaskTest() = runBlocking {
        delay(2000L)
        device.findObject(By.desc("TASKS")).click()
        delay(2000L)
        device.findObject(By.desc("YOU")).click()
        delay(2000L)
        device.pressBack()
        delay(2000L)
    }
}
