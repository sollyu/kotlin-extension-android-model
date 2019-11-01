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

package cn.maizz.kotlin.extension.android.widget

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import cn.maizz.kotlin.extension.android.content.getClipboardString
import cn.maizz.kotlin.extension.android.content.setClipboardString
import java.util.concurrent.TimeUnit

fun View.showSoftInput(flags: Int = InputMethodManager.SHOW_IMPLICIT): Boolean = (this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(this, flags)

fun View.getClipboardString(): CharSequence? = this.context.getClipboardString()

fun View.setClipboardString(text: CharSequence, label: CharSequence? = null): Unit = this.context.setClipboardString(text, label)

fun View.postDelayed(duration: Long, timeUnit: TimeUnit, callback: () -> Unit): Boolean = postDelayed(callback, timeUnit.toMillis(duration))

fun View.postDelayed(duration: Long, timeUnit: TimeUnit, runnable: Runnable): Boolean = postDelayed(runnable, timeUnit.toMillis(duration))
