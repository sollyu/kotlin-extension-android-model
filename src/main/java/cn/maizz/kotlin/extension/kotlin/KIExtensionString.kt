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

package cn.maizz.kotlin.extension.kotlin

import android.util.Base64
import java.nio.charset.Charset
import java.security.MessageDigest
import java.util.*
import java.util.regex.Pattern

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
 *
 * @param charset 字符集
 */
fun String.md5(charset: Charset = Charsets.UTF_8): String = MessageDigest.getInstance("MD5").digest(this.toByteArray(charset)).joinToString("") { String.format("%02x", it) }

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
