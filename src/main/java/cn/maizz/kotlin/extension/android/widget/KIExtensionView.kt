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

import android.annotation.TargetApi
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.showSoftInput(flags: Int = InputMethodManager.SHOW_IMPLICIT) = (this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(this, flags)

fun View.getClipboardString() = (this.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).primaryClip?.let { return@let if (it.itemCount > 0) it.getItemAt(0).text else null }

fun View.setClipboardString(text: CharSequence, label: CharSequence? = null) = (this.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(ClipData.newPlainText(label, text))

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
@Suppress("UsePropertyAccessSyntax")
fun View.setClipboardHtmlText(text: CharSequence, htmlText: String, label: CharSequence? = null) = (this.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(ClipData.newHtmlText(label, text, htmlText))
