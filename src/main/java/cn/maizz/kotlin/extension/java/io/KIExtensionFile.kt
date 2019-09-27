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

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.FileInputStream
import java.nio.charset.Charset

/**
 * 读取文本文件
 */
fun File.readAsText(encoding: Charset = Charset.forName("UTF-8")): String = FileUtils.readFileToString(this, encoding)

/**
 * 计算文件的MD5
 */
fun File.md5(): String = String(Hex.encodeHex(DigestUtils.md5(FileInputStream(this))))

/**
 * 写文件
 */
fun File.writeStringToFile(data: String, charset: Charset = Charset.forName("UTF-8"), append: Boolean = false) = FileUtils.writeStringToFile(this, data, charset, append)

/**
 * 如果文件存在就删除
 */
fun File.deleteIfExist(): Boolean = if (exists()) delete() else true

/**
 * 清空整个文件夹，包含子目录
 */
fun File.clear() = FileUtils.cleanDirectory(this)

/**
 * 文件不存在
 */
fun File.notExists(): Boolean = this.exists().not()
