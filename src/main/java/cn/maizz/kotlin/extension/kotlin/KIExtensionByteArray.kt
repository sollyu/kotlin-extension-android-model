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

package cn.maizz.kotlin.extension.kotlin

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.security.MessageDigest
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream


/**
 * 转换成16进制字符串
 */
fun ByteArray.toStringHex(): String = this.joinToString(separator = "") { it.toInt().and(0xff).toString(16).padStart(2, '0') }

/**
 * 计算MD5值
 */
fun ByteArray.md5(): String = MessageDigest.getInstance("MD5").digest(this).joinToString("") { String.format("%02x", it) }

/**
 * 使用gzip压缩
 *
 * @since 1.0.2
 */
fun ByteArray.compressGzip():ByteArray {
    val byteArrayOutputStream: ByteArrayOutputStream = ByteArrayOutputStream()
    val gzipOutputStream: GZIPOutputStream = GZIPOutputStream(byteArrayOutputStream)
    gzipOutputStream.write(this)
    gzipOutputStream.close()
    return byteArrayOutputStream.toByteArray()
}

/**
 * 使用gzip进行解压
 *
 * @since 1.0.2
 */
fun ByteArray.uncompressGzip():ByteArray {
    val byteArrayOutputStream:ByteArrayOutputStream = ByteArrayOutputStream()
    val byteArrayInputStream: ByteArrayInputStream = ByteArrayInputStream(this)
    val gzipInputStream: GZIPInputStream = GZIPInputStream(byteArrayInputStream)
    val buffer = ByteArray(256)
    var n: Int
    while (gzipInputStream.read(buffer).also { n = it } >= 0) {
        byteArrayOutputStream.write(buffer, 0, n)
    }
    return byteArrayOutputStream.toByteArray()
}