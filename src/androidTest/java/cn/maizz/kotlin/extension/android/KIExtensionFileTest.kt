/*
 * Copyright 2018 Sollyu, Wonium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.maizz.kotlin.extension.android

import android.support.test.runner.AndroidJUnit4
import cn.maizz.kotlin.extension.java.io.KIExtensionFile
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class KIExtensionFileTest : KIExtensionFile {

    @Test
    fun readFileToByteArray() {
        val f = File("/data/data/cn.maizz.kotlin.extension.android.test/files/test.tmp").apply { this.parentFile.mkdirs(); }
        f.writeText("kotlin-extension-android")
        Assert.assertEquals(f.md5(), "4DE01A6FB114971C48C4495DA70F23B2")
    }
}