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

import org.junit.Assert
import org.junit.Test

class KIExtensionByteArrayKtTest {

    @Test
    fun toStringHex() {
        Assert.assertEquals("0c202b", byteArrayOf(12, 32, 43).toStringHex())
    }

    @Test
    fun md5() {
        Assert.assertEquals("d0f034d30f1ad53d36f6d30d18a413fe", byteArrayOf(12, 32, 43).md5().toStringHex())
    }

    @Test
    fun base64Encode() {
        Assert.assertArrayEquals("YWRtaW4=".toByteArray(), "admin".toByteArray().base64Encode())
    }

    @Test
    fun base64Decode() {
        Assert.assertArrayEquals("admin".toByteArray(), "YWRtaW4=".toByteArray().base64Decode())
    }

    @Test
    fun sha1() {
        Assert.assertEquals("d033e22ae348aeb5660fc2140aec35850c4da997", "admin".toByteArray().sha1().toStringHex())
    }
}