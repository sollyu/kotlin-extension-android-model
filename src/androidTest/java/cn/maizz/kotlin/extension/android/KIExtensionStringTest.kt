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

package cn.maizz.kotlin.extension.android

import android.support.test.runner.AndroidJUnit4
import cn.maizz.kotlin.extension.kotlin.KIExtensionString
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KIExtensionStringTest : KIExtensionString {

    @Test
    fun base64Encode() {
        assertEquals("admin".base64Encode(), "YWRtaW4=")
    }

    @Test
    fun base64Decode() {
        assertEquals("YWRtaW4=".base64Decode(), "admin")
    }

    @Test
    fun md5() {
        assertEquals("admin".md5(), "21232F297A57A5A743894A0E4A801FC3")
    }

    @Test
    fun mosaic() {
        assertEquals("13297713450".mosaic('*', 3, 6), "132****3450")
    }

    @Test
    fun randomTest() {
        assertEquals("1234567890".random(20).length, 20)
        assertEquals("1234567890".random(50).length, 50)
        assertEquals("abcdefghijklmnopqrstuvwxyz".random(50).length, 50)
        assertEquals("这件事情出现在大家眼前,到底是真是假?按照大家".random(50).length, 50)
    }

    @Test
    fun isContainChineseTest() {
        assertEquals("sollyu".isContainChinese(), false)
        assertEquals("sollyu最牛逼".isContainChinese(), true)
        assertEquals("sollyu，very good".isContainChinese(), false)
    }

    @Test
    fun toUpperCaseTest() {
        assertEquals("wonium".toUpperCase(1), "Wonium")
        assertEquals("wonium".toUpperCase(3), "WONium")
    }

    @Test
    fun toLowerCaseTest() {
        assertEquals("WONIum".toLowerCase(1), "wONIum")
        assertEquals("WONIum".toLowerCase(3), "wonIum")
    }
}