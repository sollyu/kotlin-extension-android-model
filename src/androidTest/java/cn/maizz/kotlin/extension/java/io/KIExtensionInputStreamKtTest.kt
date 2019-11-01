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
import org.junit.Before
import org.junit.Test
import java.io.File
import java.io.FileInputStream
import java.nio.charset.Charset

class KIExtensionInputStreamKtTest {

    private val targetFile: File = File("/data/data/cn.maizz.kotlin.extension.android.test/files/file.test.tmp")
    private val charset: Charset = Charset.forName("UTF-8")
    private val fileContent: String = "i love china."

    @Before
    fun setUp() {
        targetFile.deleteIfExist()
        targetFile.writeStringToFile(fileContent, charset)
    }

    @Test
    fun toString1() {
        val fileInputStream: FileInputStream = FileInputStream(targetFile)
        Assert.assertEquals(fileInputStream.toString(charset), fileContent)
    }

    @Test
    fun toByteArray() {
        val fileInputStream: FileInputStream = FileInputStream(targetFile)
        Assert.assertArrayEquals(fileInputStream.toByteArray(), byteArrayOf(105, 32, 108, 111, 118, 101, 32, 99, 104, 105, 110, 97, 46))
    }

    @Test
    fun toCharArray() {
        val fileInputStream: FileInputStream = FileInputStream(targetFile)
        Assert.assertArrayEquals(fileInputStream.toCharArray(), charArrayOf('i', ' ', 'l', 'o', 'v', 'e', ' ', 'c', 'h', 'i', 'n', 'a', '.'))
    }

    @Test
    fun equal() {
        val fileInputStream1: FileInputStream = FileInputStream(targetFile)
        val fileInputStream2: FileInputStream = FileInputStream(targetFile)
        Assert.assertTrue(fileInputStream1.equal(fileInputStream2))
    }

    @Test
    fun copy() {
        targetFile.parentFile?.clear()
        targetFile.mkdirParent()
        Assert.assertEquals(targetFile.list()?.size ?: 0, 0)

        val srcFile = File(targetFile.parentFile, "test1.txt")
        val desFile = File(targetFile.parentFile, "test2.txt")
        Assert.assertTrue(desFile.notExists())

        srcFile.writeStringToFile(fileContent, charset)

        FileInputStream(srcFile).copy(desFile)

        Assert.assertTrue(desFile.exists())
        Assert.assertEquals(desFile.readAsText(charset), fileContent)
    }

    @Test
    fun readLines() {
        targetFile.deleteIfExist()

        val writeLines: ArrayList<String> = arrayListOf(fileContent, fileContent, fileContent)
        targetFile.writeLines(writeLines, charset)
        Assert.assertArrayEquals(targetFile.readLines(charset).toTypedArray(), writeLines.toTypedArray())

        Assert.assertArrayEquals(FileInputStream(targetFile).readLines(charset).toTypedArray(), writeLines.toTypedArray())
    }
}