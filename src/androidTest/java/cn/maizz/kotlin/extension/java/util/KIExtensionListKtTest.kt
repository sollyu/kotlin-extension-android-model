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

package cn.maizz.kotlin.extension.java.util

import org.junit.Assert
import org.junit.Test
import java.util.*

class KIExtensionListKtTest {

    @Test
    fun random() {
        val origin: List<String> = arrayListOf("i", "love", "china")
        val random: String = origin.random(Random())

        Assert.assertTrue(origin.indexOf(random) != -1)
    }
}