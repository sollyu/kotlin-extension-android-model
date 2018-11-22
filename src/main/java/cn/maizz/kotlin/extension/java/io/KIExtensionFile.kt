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

package cn.maizz.kotlin.extension.java.io

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.FileInputStream
import java.nio.charset.Charset

interface KIExtensionFile {
    fun File.md5(): String = String(Hex.encodeHex(DigestUtils.md5(FileInputStream(this)))).toUpperCase()

    fun File.writeStringToFile(data: String, charset: Charset = Charset.forName("UTF-8"), append: Boolean = false) = FileUtils.writeStringToFile(this, data, charset, append)

    /**
     * 如果文件存在就删除
     */
    fun File.deleteIfExist():Boolean = if (exists()) delete() else true
}