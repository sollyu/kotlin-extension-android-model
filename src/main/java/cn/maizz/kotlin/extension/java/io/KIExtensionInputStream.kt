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

import org.apache.commons.io.IOUtils
import java.io.InputStream
import java.nio.charset.Charset

@Suppress("unused")
interface KIExtensionInputStream {

    fun InputStream.toString(encoding: Charset) = IOUtils.toString(this, encoding)!!

    fun InputStream.toByteArray() = IOUtils.toByteArray(this)!!

    fun InputStream.toCharArray(encoding: Charset = Charset.forName("UTF-8")) = IOUtils.toCharArray(this, encoding)!!

    fun InputStream.equal(inputStream: InputStream) = IOUtils.contentEquals(this, inputStream)
}