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

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

@Suppress("unused", "MemberVisibilityCanBePrivate")
interface KIExtensionDate {

    fun Date.format(format: String = "yyyy-MM-dd HH:mm:ss", locale: Locale = Locale.getDefault()) = SimpleDateFormat(format, locale).format(this)

    /**
     * 是否是今天
     */
    fun Date.isToday(): Boolean = DateUtils.isToday(this.time)

    /**
     * 是否是一个未来的时间
     */
    fun Date.isFuture(): Boolean = this.time > System.currentTimeMillis()

    /**
     * 当前日期是否为明天
     */
    fun Date.isTomorrow(): Boolean {
        val todayCalendar: Calendar = Calendar.getInstance().apply { this.add(Calendar.DAY_OF_MONTH, 1) }
        val tomorrowCalendar: Calendar = Calendar.getInstance().apply { this.timeInMillis = this@isTomorrow.time }
        return (todayCalendar.get(Calendar.YEAR) == tomorrowCalendar.get(Calendar.YEAR) && todayCalendar.get(Calendar.MONTH) == tomorrowCalendar.get(Calendar.MONTH) && todayCalendar.get(Calendar.DAY_OF_MONTH) == tomorrowCalendar.get(Calendar.DAY_OF_MONTH))
    }

    /**
     * 当前日期相对于今天是否是后天
     */
    fun Date.isAfterTomorrow(): Boolean {
        val todayCalendar: Calendar = Calendar.getInstance().apply { this.add(Calendar.DAY_OF_MONTH, 2) }
        val tomorrowCalendar: Calendar = Calendar.getInstance().apply { this.timeInMillis = this@isAfterTomorrow.time }
        return (todayCalendar.get(Calendar.YEAR) == tomorrowCalendar.get(Calendar.YEAR) && todayCalendar.get(Calendar.MONTH) == tomorrowCalendar.get(Calendar.MONTH) && todayCalendar.get(Calendar.DAY_OF_MONTH) == tomorrowCalendar.get(Calendar.DAY_OF_MONTH))
    }

    /**
     * 使一个日期相加
     * @param field  单位，默认天
     * @param amount 数量，默认1
     */
    fun Date.add(field: Int = Calendar.DAY_OF_MONTH, amount: Int = 1): Date = Calendar.getInstance().apply { this.timeInMillis = this@add.time; this.add(field, amount) }.time

    /**
     * 转换成Calendar对象
     */
    fun Date.toCalendar(): Calendar = Calendar.getInstance().apply { time = this.time }
}