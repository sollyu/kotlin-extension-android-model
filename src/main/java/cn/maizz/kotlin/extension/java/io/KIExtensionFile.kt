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
import java.io.OutputStream
import java.nio.charset.Charset
import java.util.*

/**
 * 转换为FileInputStream对象
 */
fun File.inputStream() = FileInputStream(this)

/**
 * 读取文本文件
 */
fun File.readAsText(encoding: Charset = Charset.forName("UTF-8")): String = FileUtils.readFileToString(this, encoding)

/**
 * 计算文件的MD5
 */
fun File.md5(): String = String(Hex.encodeHex(DigestUtils.md5(this.inputStream())))

/**
 * 计算文件sha1
 */
fun File.sha1(): String = String(Hex.encodeHex(DigestUtils.sha1(this.inputStream())))

/**
 * 计算文件的sha256
 */
fun File.sha256(): String = String(Hex.encodeHex(DigestUtils.sha256(this.inputStream())))

/**
 * 计算文件的sha384
 */
fun File.sha384(): String = String(Hex.encodeHex(DigestUtils.sha384(this.inputStream())))

/**
 * 计算文件的sha512
 */
fun File.sha512(): String = String(Hex.encodeHex(DigestUtils.sha512(this.inputStream())))

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
fun File.clear(): Unit = FileUtils.cleanDirectory(this)

/**
 * 文件不存在
 */
fun File.notExists(): Boolean = this.exists().not()

/**
 * 指定文件最后修改时间
 */
fun File.lastModifiedTime(): Date = Date(this.lastModified())

/**
 * 创建所给文件或目录的父目录
 */
fun File.mkdirParent(): Boolean = this.parentFile?.mkdirs() ?: false

/**
 * 复制文件
 *
 * @param dest 目标文件
 * @param date 重写文件日期
 *
 * @see FileUtils.copyFile
 */
fun File.copy(dest: File, date: Boolean = true): Unit = FileUtils.copyFile(this, dest, date)

/**
 * 复制文件
 *
 * @param outputStream
 *
 */
fun File.copy(outputStream: OutputStream): Long = FileUtils.copyFile(this, outputStream)

/**
 * 作为文件夹进行复制
 *
 * @param dest 目标文件夹
 *
 * @see FileUtils.copyDirectory
 */
fun File.copyAsDirectory(dest: File, date: Boolean = true): Unit = FileUtils.copyDirectory(this, dest, date)

/**
 * 将文件移动到另外一个地方
 *
 * @param dest 目标文件
 *
 * @see FileUtils.moveFile
 */
fun File.move(dest: File): Unit = FileUtils.moveFile(this, dest)

/**
 * 移动到目录
 *
 * @param destDir       目标文件夹
 * @param createDestDir 目标文件夹不存在时是否创建文件夹
 *
 * @see FileUtils.moveFileToDirectory
 */
fun File.moveToDirectory(destDir: File, createDestDir: Boolean = true): Unit = FileUtils.moveFileToDirectory(this, destDir, createDestDir)

/**
 * 写入文件行
 *
 * @param collection 文件行
 * @param encoding 字符集
 *
 * @see FileUtils.writeStringToFile
 */
fun File.writeLines(collection: Collection<*>, encoding: Charset = Charset.forName("UTF-8")): Unit = FileUtils.writeLines(this, encoding.displayName(), collection)
