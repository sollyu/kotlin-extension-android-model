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

interface KIExtensionByte {

    fun Byte.setBitValue(value: Byte, position: Int): Byte = ((value.toInt() shl position) or this.toInt()).toByte()

    fun Byte.toBit(): String =
            ((this.toInt() shr 7) and 0x1).toString() + ((this.toInt() shr 6) and 0x1).toString() + ((this.toInt() shr 5) and 0x1).toString() + ((this.toInt() shr 4) and 0x1).toString() +
                    ((this.toInt() shr 3) and 0x1).toString() + ((this.toInt() shr 2) and 0x1).toString() + ((this.toInt() shr 1) and 0x1).toString() + ((this.toInt() shr 0) and 0x1).toString()
}