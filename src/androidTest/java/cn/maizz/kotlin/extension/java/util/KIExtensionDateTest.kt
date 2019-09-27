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

package cn.maizz.kotlin.extension.java.util

import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class KIExtensionDateTest  {

    @Test
    fun format() {
        assertEquals(Date(666220860000).format("yyyy-MM-dd HH:mm:ss"), "1991-02-11 05:21:00")
        assertEquals(Date(666220860000).format("yyyy/MM/dd HH:mm:ss"), "1991/02/11 05:21:00")
    }

    @Test
    fun add() {
        assertEquals(Date(666220860000).add(Calendar.DAY_OF_MONTH, 4).format("yyyy-MM-dd HH:mm:ss"), "1991-02-15 05:21:00")
        assertEquals(Date(666220860000).add(Calendar.MONTH       , 4).format("yyyy/MM/dd HH:mm:ss"), "1991/06/11 05:21:00")
        assertEquals(Date(666220860000).add(Calendar.MINUTE      , 4).format("yyyy/MM/dd HH:mm:ss"), "1991/02/11 05:25:00")
        assertEquals(Date(666220860000).add(Calendar.SECOND      , 4).format("yyyy/MM/dd HH:mm:ss"), "1991/02/11 05:21:04")

        assertEquals(Date(666220860000).add(Calendar.DAY_OF_MONTH, -1).format("yyyy-MM-dd HH:mm:ss"), "1991-02-10 05:21:00")
        assertEquals(Date(666220860000).add(Calendar.MINUTE      , -1).format("yyyy-MM-dd HH:mm:ss"), "1991-02-11 05:20:00")
    }

    @Test
    fun isToday() {
        // 请注意，如果您测试的时间在23:30之后，此处会有错误
        assertEquals(Date().add(Calendar.MINUTE      , 30 ).isToday(), true)
        assertEquals(Date().add(Calendar.MINUTE      , -30).isToday(), true)
        assertEquals(Date().add(Calendar.DAY_OF_MONTH, 30 ).isToday(), false)
        assertEquals(Date().add(Calendar.DAY_OF_MONTH, -30).isToday(), false)
    }

    @Test
    fun isTomorrow() {
        // 请注意，如果您测试的时间在23:30之后，此处会有错误
        assertEquals(Date().add(Calendar.MINUTE      , 30 ).isTomorrow(), false)
        assertEquals(Date().add(Calendar.MINUTE      , -30).isTomorrow(), false)
        assertEquals(Date().add(Calendar.DAY_OF_MONTH, 1  ).isTomorrow(), true)
        assertEquals(Date().add(Calendar.DAY_OF_MONTH, -1 ).isTomorrow(), false)
    }

    @Test
    fun isFuture() {
        assertEquals(Date().add(Calendar.MINUTE      , 30 ).isFuture(), true)
        assertEquals(Date().add(Calendar.MINUTE      , -30).isFuture(), false)
        assertEquals(Date().add(Calendar.DAY_OF_MONTH, 30 ).isFuture(), true)
        assertEquals(Date().add(Calendar.DAY_OF_MONTH, -30).isFuture(), false)
    }

    @Test
    fun day() {
        assertEquals(Date(666220860000)      .format("yyyy-MM-dd HH:mm:ss"), "1991-02-11 05:21:00")
        assertEquals(Date(666220860000).day().format("yyyy-MM-dd HH:mm:ss"), "1991-02-11 00:00:00")
        assertEquals(Date(666220860000).day().format("yyyy/MM/dd HH:mm:ss"), "1991/02/11 00:00:00")
    }

    @Test
    fun dayMonth() {
        assertEquals(Date(666220860000).format("yyyy-MM-dd HH:mm:ss"), "1991-02-11 05:21:00")
        assertEquals(Date(666220860000).dayMonth(), 11)
    }

    @Test
    fun sub() {
        val startDate:Date = Date(666220860000)
        val endDate:Date = Date(666566460000)

        assertEquals(startDate.format("yyyy-MM-dd HH:mm:ss"), "1991-02-11 05:21:00")
        assertEquals(endDate  .format("yyyy-MM-dd HH:mm:ss"), "1991-02-15 05:21:00")
        assertEquals(startDate.sub(endDate).time, TimeUnit.HOURS.toMillis(-96))
        assertEquals(startDate.sub(endDate).time, TimeUnit.DAYS.toMillis(-4))
        assertEquals(endDate.sub(startDate).time, TimeUnit.HOURS.toMillis(96))
        assertEquals(endDate.sub(startDate).time, TimeUnit.DAYS.toMillis(4))
    }
}