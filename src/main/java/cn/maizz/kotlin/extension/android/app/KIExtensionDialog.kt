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

package cn.maizz.kotlin.extension.android.app

import android.app.Dialog
import android.view.View
import java.util.concurrent.TimeUnit

/**
 * 一个自动关闭的dialog
 *
 * @param timeout 超时时间 单位毫秒
 *
 * @since 1.0.0
 */
fun Dialog.show(timeout: Long) {
    this@show.show()
    this.window!!.decorView.findViewById<View>(android.R.id.content).postDelayed({ this.dismiss() }, timeout)
}

/**
 * 一个自动关闭的Dialog
 *
 * @param timeout 超时时间 单位毫秒
 * @param callback 关闭后的回调 会复写setOnDismissListener
 *
 * @since 1.0.0
 */
fun Dialog.show(timeout: Long, callback: () -> Unit) {
    this@show.show()
    this.setOnDismissListener { callback() }
    this.window!!.decorView.findViewById<View>(android.R.id.content).postDelayed({ this.dismiss() }, timeout)
}

/**
 * 一个自动关闭的Dialog
 *
 * @param timeout 超时时间
 * @param timeUnit 时间单位
 * @param callback 关闭后的回调 会复写setOnDismissListener
 *
 * @since 1.0.2
 */
fun Dialog.show(timeout: Long, timeUnit: TimeUnit = TimeUnit.SECONDS, callback: () -> Unit): Unit = show(timeUnit.toMillis(timeout), callback)
