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

package cn.maizz.kotlin.extension.android.widget

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView

interface KIExtensionEditView : KIExtensionTextView {

    fun EditText.onImeAction(actionListener: (textView: TextView, actionId: Int, keyEvent: KeyEvent?) -> Boolean) = this.setOnEditorActionListener { v, actionId, event -> return@setOnEditorActionListener actionListener(v, actionId, event) }
    fun EditText.onImeAction(actionListener: TextView.OnEditorActionListener) = this.onImeActionDone { textView, actionId, keyEvent -> actionListener.onEditorAction(textView, actionId, keyEvent) }

    fun EditText.onImeActionDone(actionListener: (textView: TextView, actionId: Int, keyEvent: KeyEvent?) -> Boolean) = this.setOnEditorActionListener { v, actionId, event -> return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_DONE) actionListener(v, actionId, event) else false }
    fun EditText.onImeActionDone(actionListener: TextView.OnEditorActionListener) = this.onImeActionDone { textView, actionId, keyEvent -> actionListener.onEditorAction(textView, actionId, keyEvent) }

    fun EditText.onImeActionNext(actionListener: (textView: TextView, actionId: Int, keyEvent: KeyEvent?) -> Boolean) = this.setOnEditorActionListener { v, actionId, event -> return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_NEXT) actionListener(v, actionId, event) else false }
    fun EditText.onImeActionNext(actionListener: TextView.OnEditorActionListener) = this.onImeActionDone { textView, actionId, keyEvent -> actionListener.onEditorAction(textView, actionId, keyEvent) }

    fun EditText.onImeActionSend(actionListener: (textView: TextView, actionId: Int, keyEvent: KeyEvent?) -> Boolean) = this.setOnEditorActionListener { v, actionId, event -> return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_SEND) actionListener(v, actionId, event) else false }
    fun EditText.onImeActionSend(actionListener: TextView.OnEditorActionListener) = this.onImeActionDone { textView, actionId, keyEvent -> actionListener.onEditorAction(textView, actionId, keyEvent) }

    fun EditText.onImeActionSearch(actionListener: (textView: TextView, actionId: Int, keyEvent: KeyEvent?) -> Boolean) = this.setOnEditorActionListener { v, actionId, event -> return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_SEARCH) actionListener(v, actionId, event) else false }
    fun EditText.onImeActionSearch(actionListener: TextView.OnEditorActionListener) = this.onImeActionDone { textView, actionId, keyEvent -> actionListener.onEditorAction(textView, actionId, keyEvent) }

    fun EditText.onImeActionGo(actionListener: (textView: TextView, actionId: Int, keyEvent: KeyEvent?) -> Boolean) = this.setOnEditorActionListener { v, actionId, event -> return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_GO) actionListener(v, actionId, event) else false }
    fun EditText.onImeActionGo(actionListener: TextView.OnEditorActionListener) = this.onImeActionDone { textView, actionId, keyEvent -> actionListener.onEditorAction(textView, actionId, keyEvent) }

}