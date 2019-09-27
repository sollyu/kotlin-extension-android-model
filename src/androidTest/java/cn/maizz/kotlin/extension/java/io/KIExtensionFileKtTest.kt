/*
 * Copyright 2018-2019 Sollyu, Wonium
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
 *
 */

package cn.maizz.kotlin.extension.java.io

import org.junit.Assert
import org.junit.Test
import java.io.File
import java.nio.charset.Charset

class KIExtensionFileKtTest {

    private val targetFile: File = File("/data/data/cn.maizz.kotlin.extension.android.test/files/file.test.tmp")
    private val charset: Charset = Charset.forName("UTF-8")
    private val fileContent: String = "i love china."

    @Test
    fun readAsText() {
        targetFile.deleteIfExist()
        targetFile.writeStringToFile(fileContent, charset)
        Assert.assertEquals(targetFile.readAsText(charset), fileContent)
    }

    @Test
    fun md5() {
        targetFile.deleteIfExist()
        targetFile.writeStringToFile(fileContent, charset)

        Assert.assertEquals(targetFile.md5(), "cc5c5b6d68923ce66b057544ed0e7f05")
    }

    @Test
    fun clear() {
        targetFile.deleteIfExist()
        targetFile.writeStringToFile(fileContent, charset)

        Assert.assertTrue(targetFile.parentFile?.list()?.size ?: 0 >= 1)
        targetFile.parentFile?.clear()
        Assert.assertEquals(targetFile.parentFile?.list()?.size, 0)
    }

    @Test
    fun notExists() {
        targetFile.deleteIfExist()
        Assert.assertEquals(targetFile.notExists(), true)
    }
}