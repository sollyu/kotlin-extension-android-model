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

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.io.File


/**
 * 转换成16进制字符串
 */
fun ByteArray.toStringHex(): String = this.joinToString(separator = "") { it.toInt().and(0xff).toString(16).padStart(2, '0') }

/**
 * 计算MD5值
 */
fun ByteArray.md5(): String = String(Hex.encodeHex(DigestUtils.md5(this)))

/**
 * 计算SHA1值
 * @since 1.0.2
 * TODO: 补充测试用例
 */
fun ByteArray.sha1(): String = String(Hex.encodeHex(DigestUtils.sha1(this)))

/**
 * 计算文件的sha256
 * @since 1.0.2
 * TODO: 补充测试用例
 */
fun ByteArray.sha256(): String = String(Hex.encodeHex(DigestUtils.sha256(this)))

/**
 * 计算文件的sha384
 * @since 1.0.2
 * TODO: 补充测试用例
 */
fun ByteArray.sha384(): String = String(Hex.encodeHex(DigestUtils.sha384(this)))

/**
 * 计算文件的sha512
 *
 * TODO: 补充测试用例
 * @since 1.0.2
 */
fun ByteArray.sha512(): String = String(Hex.encodeHex(DigestUtils.sha512(this)))

/**
 * 写入到文件
 *
 * @param file 文件
 * @param append 是否追加写入
 *
 * @see File.writeBytes
 * @since 1.0.2
 *
 * TODO: 补充测试用例
 */
fun ByteArray.write(file: File, append: Boolean = false): Unit = if (append) file.appendBytes(array = this) else file.writeBytes(array = this)