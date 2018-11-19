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

interface KIExtensionString {

    fun String.Companion.random(length: Int): String = (1..length).map { "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"[Random().nextInt(62)] }.joinToString("")

    /**
     * 字符串打马赛克 例如：13297713450 => 132****3450
     *
     * @param char          打码显示的字符
     * @param startPosition 起始位置
     * @param endPosition   结束未知
     */
    fun String.mosaic(char: Char = '*', startPosition: Int = 1, endPosition: Int = length): String = StringBuilder().also { stringBuilder -> (0 until length).forEach { i -> stringBuilder.append(if (i in startPosition..endPosition) char else this@mosaic[i]) } }.toString()

    fun String.base64Encode(charset: Charset = Charsets.UTF_8, base64Flag: Int = Base64.NO_WRAP): String = Base64.encodeToString(this.toByteArray(charset), base64Flag)
    fun String.base64Decode(charset: Charset = Charsets.UTF_8, base64Flag: Int = Base64.NO_WRAP): String = String(Base64.decode(this, base64Flag), charset)

    fun String.md5(charset: Charset = Charsets.UTF_8, useUpper: Boolean = true) = MessageDigest.getInstance("MD5").digest(this.toByteArray(charset)).joinToString("") { String.format(if (useUpper) "%02X" else "%02x", it) }

    fun String.random(length: Int = 10, random: Random = Random(System.currentTimeMillis())): String = (0 until length).map { this[random.nextInt(this.length)] }.joinToString("")

    /**
     * 判断是否保护中文
     */
    fun String.isContainChinese():Boolean = this.contains(Pattern.compile("[\u4e00-\u9fa5]").toRegex())

    /**
     * @param endIndex 结束位置
     */
    fun String.toUpperCase(endIndex: Int): String = this.substring(0, endIndex).toUpperCase() + this.substring(endIndex)

    /**
     * @param endIndex 结束位置
     */
    fun String.toLowerCase(endIndex: Int): String = this.substring(0, endIndex).toLowerCase() + this.substring(endIndex)
}