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

import cn.maizz.kotlin.extension.java.util.format
import org.junit.Assert
import org.junit.Test
import java.io.File
import java.io.FileNotFoundException
import java.nio.charset.Charset
import java.util.*

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
    fun sha1() {
        targetFile.deleteIfExist()
        targetFile.writeStringToFile(fileContent, charset)

        Assert.assertEquals(targetFile.sha1(), "30a787a42cf34d42bc0dc0a11b2ceed238cf7923")
    }

    @Test
    fun sha256() {
        targetFile.deleteIfExist()
        targetFile.writeStringToFile(fileContent, charset)

        Assert.assertEquals(targetFile.sha256(), "b2f5eb0e7264a911f0fd4358cced924a0140ecf5d57e1ee717f18782134da2c3")
    }

    @Test
    fun sha384() {
        targetFile.deleteIfExist()
        targetFile.writeStringToFile(fileContent, charset)

        Assert.assertEquals(targetFile.sha384(), "573cea0e961369e843ee0a9ebfe7b858e90d2c7ce3a4aa237b50743e9831f958e5aa5e644dc861813b282d27fc9bdfcf")
    }

    @Test
    fun sha512() {
        targetFile.deleteIfExist()
        targetFile.writeStringToFile(fileContent, charset)

        Assert.assertEquals(targetFile.sha512(), "dd24e441aeda8e61c7a10ab96fbc0379827c2d4d38338bb373712343df3144df08593944d05d458e1b09d7cbca2eca895b8dfd2b476c162527686f5a671608b1")
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

    @Test
    fun lastModifiedTime() {
        targetFile.deleteIfExist()
        targetFile.writeStringToFile(fileContent, charset)
        Assert.assertEquals(Date().format(), targetFile.lastModifiedTime().format())
    }

    @Test
    fun mkdirParent() {
        targetFile.parentFile?.clear()
        val subFile = File(targetFile.parentFile, "china/good/me.txt")
        Assert.assertTrue(subFile.mkdirParent() ?: false)
        Assert.assertTrue(File(targetFile.parentFile, "china/good").isDirectory)
        targetFile.parentFile?.clear()
    }

    @Test
    fun copyAsDirectory() {
        targetFile.parentFile?.clear()
        val subFile = File(targetFile.parentFile, "china/good/me.txt")
        subFile.mkdirParent()
        subFile.createNewFile()
        Assert.assertTrue(subFile.exists())
        subFile.parentFile?.copyAsDirectory(File(targetFile.parentFile, "china/nice"))
        Assert.assertTrue(File(targetFile.parentFile, "china/nice/me.txt").exists())
        targetFile.parentFile?.clear()
    }

    @Test
    fun move() {
        targetFile.parentFile?.clear()
        val srcFile = File(targetFile.parentFile, "china/good/me.txt")
        val desFile = File(targetFile.parentFile, "me.log")

        srcFile.mkdirParent()
        srcFile.createNewFile()
        Assert.assertTrue(srcFile.exists())

        srcFile.move(desFile)
        Assert.assertTrue(srcFile.notExists())
        Assert.assertTrue(desFile.exists())
        targetFile.parentFile?.clear()
    }

    @Test
    fun moveToDirectory() {
        targetFile.parentFile?.clear()
        val srcFile = File(targetFile.parentFile, "china/good/me.txt")

        srcFile.mkdirParent()
        srcFile.createNewFile()
        Assert.assertTrue(srcFile.exists())

        srcFile.moveToDirectory(targetFile.parentFile ?: throw FileNotFoundException())
        Assert.assertTrue(srcFile.notExists())
        Assert.assertTrue(File(targetFile.parentFile, "me.txt").exists())
        targetFile.parentFile?.clear()
    }

    @Test
    fun writeLines() {
        targetFile.parentFile?.clear()
        targetFile.writeLines(arrayListOf("I love China.", "China Number 1."))
        Assert.assertArrayEquals(targetFile.readLines().toTypedArray(), arrayOf("I love China.", "China Number 1."))
        targetFile.parentFile?.clear()
    }
}