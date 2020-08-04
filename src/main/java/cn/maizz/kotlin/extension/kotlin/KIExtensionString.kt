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

import android.util.Base64
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.charset.Charset
import java.util.*
import java.util.regex.Pattern
import java.util.zip.GZIPOutputStream

/**
 * 静态方法 从一个字符串中随机字符串
 *
 * @param length 长度
 * @param origin 内容
 * @param random 种子
 */
fun String.Companion.random(length: Int, origin: String = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", random: Random = Random()): String = (1..length).map { origin[random.nextInt(origin.length - 1)] }.joinToString("")

/**
 * 字符串打马赛克 例如：13288888888 => 132****8888
 *
 * @param char          打码显示的字符
 * @param startPosition 起始位置
 * @param endPosition   结束未知
 */
fun String.mosaic(char: Char = '*', startPosition: Int = 1, endPosition: Int = length): String = StringBuilder().also { stringBuilder -> (0 until length).forEach { i -> stringBuilder.append(if (i in startPosition..endPosition) char else this@mosaic[i]) } }.toString()

/**
 * Base64加密
 *
 * @param charset 字符集
 * @param flag 标志
 */
fun String.base64Encode(charset: Charset = Charsets.UTF_8, flag: Int = Base64.NO_WRAP): String = Base64.encodeToString(this.toByteArray(charset), flag)

/**
 * Base64解密
 *
 * @param charset   字符集
 * @param flag      标志
 */
fun String.base64Decode(charset: Charset = Charsets.UTF_8, flag: Int = Base64.NO_WRAP): String = String(Base64.decode(this, flag), charset)

/**
 * MD5
 */
fun String.md5(charset: Charset = Charsets.UTF_8): String = String(Hex.encodeHex(DigestUtils.md5(this.toByteArray(charset))))

/**
 * 计算文件sha1
 */
fun String.sha1(charset: Charset = Charsets.UTF_8): String = String(Hex.encodeHex(DigestUtils.sha1(this.toByteArray(charset))))

/**
 * 计算文件的sha256
 */
fun String.sha256(charset: Charset = Charsets.UTF_8): String = String(Hex.encodeHex(DigestUtils.sha256(this.toByteArray(charset))))

/**
 * 计算文件的sha384
 */
fun String.sha384(charset: Charset = Charsets.UTF_8): String = String(Hex.encodeHex(DigestUtils.sha384(this.toByteArray(charset))))

/**
 * 计算文件的sha512
 */
fun String.sha512(charset: Charset = Charsets.UTF_8): String = String(Hex.encodeHex(DigestUtils.sha512(this.toByteArray(charset))))

/**
 * 使用当前字符集进行随机
 *
 * @param length 长度
 * @param random 种子
 */
fun String.random(length: Int = 10, random: Random = Random()): String = (0 until length).map { this[random.nextInt(this.length)] }.joinToString("")

/**
 * 判断是否包含中文
 */
fun String.isContainChinese(): Boolean = this.contains(Pattern.compile("[\u4e00-\u9fa5]").toRegex())

/**
 * 判断是否一个邮箱
 */
fun String.isEmailValid(): Boolean = Pattern.compile("^\\w+@\\w+\\.\\w{2,}\$").matcher(this).matches()

/**
 * 判断是否一个中国手机号码
 */
fun String.isPhoneNumber(): Boolean = Pattern.compile("^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(14[5-9])|(166)|(19[8,9])|)\\d{8}$").matcher(this).matches()

/**
 * 当作一个File对象
 * @since 1.0.0
 */
fun String.asFile(): File = File(this)

/**
 * 当作一个十六进制字符串解析
 *
 * @since 1.0.2
 */
fun String.asHexString(): ByteArray = Hex.decodeHex(this.toCharArray())

/**
 * 当作一个十六进制字符串解析
 *
 * @since 1.0.2
 */
fun String.decodeHex(): ByteArray = asHexString()

/**
 * 使用gzip压缩字符串
 *
 * @since 1.0.2
 */
fun String.compressGzip(charset: Charset = Charsets.UTF_8): ByteArray = this.toByteArray(charset).compressGzip()

