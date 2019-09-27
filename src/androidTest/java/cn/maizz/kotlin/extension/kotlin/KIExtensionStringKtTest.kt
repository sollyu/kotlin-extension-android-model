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


class KIExtensionStringKtTest {

    @Test
    fun random() {
        Assert.assertEquals("1234567890".random(20).length, 20)
        Assert.assertEquals("1234567890".random(50).length, 50)
        Assert.assertEquals("abcdefghijklmnopqrstuvwxyz".random(50).length, 50)
        Assert.assertEquals("这件事情出现在大家眼前,到底是真是假?按照大家".random(50).length, 50)
    }

    @Test
    fun mosaic() {
        Assert.assertEquals("13297713450".mosaic('*', 3, 6), "132****3450")
    }

    @Test
    fun base64Encode() {
        Assert.assertEquals("admin".base64Encode(), "YWRtaW4=")
    }

    @Test
    fun base64Decode() {
        Assert.assertEquals("YWRtaW4=".base64Decode(), "admin")
    }

    @Test
    fun md5() {
        Assert.assertEquals("admin".md5(), "21232f297a57a5a743894a0e4a801fc3")
    }

    @Test
    fun random1() {
        Assert.assertEquals(String.random(20, "1234567890").length, 20)
        Assert.assertEquals(String.random(50, "1234567890").length, 50)
        Assert.assertEquals(String.random(50, "abcdefghijklmnopqrstuvwxyz").length, 50)
        Assert.assertEquals(String.random(50, "这件事情出现在大家眼前,到底是真是假?按照大家").length, 50)
    }

    @Test
    fun isContainChinese() {
        Assert.assertEquals("i love china".isContainChinese(), false)
        Assert.assertEquals("china牛逼".isContainChinese(), true)
        Assert.assertEquals("i love china，very good".isContainChinese(), false)
    }

    @Test
    fun isEmailValid() {
        Assert.assertTrue("china@number.one".isEmailValid())
        Assert.assertTrue("110@china.cn".isEmailValid())
        Assert.assertFalse("china#number.one".isEmailValid())
        Assert.assertFalse("i china#number.one".isEmailValid())
    }

    @Test
    fun isPhoneNumber() {
        Assert.assertTrue("18888888888".isPhoneNumber())
        Assert.assertFalse("188888888888".isEmailValid())
        Assert.assertFalse("+8618888888888".isEmailValid())
        Assert.assertFalse("a8888888888".isEmailValid())
    }
}