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

package cn.maizz.kotlin.extension

import cn.maizz.kotlin.extension.android.content.KIExtensionContext
import cn.maizz.kotlin.extension.android.widget.KIExtensionEditView
import cn.maizz.kotlin.extension.android.widget.KIExtensionTextView
import cn.maizz.kotlin.extension.android.widget.KIExtensionView
import cn.maizz.kotlin.extension.java.io.KIExtensionFile
import cn.maizz.kotlin.extension.java.io.KIExtensionInputStream
import cn.maizz.kotlin.extension.java.util.KIExtensionDate
import cn.maizz.kotlin.extension.java.util.KIExtensionList
import cn.maizz.kotlin.extension.kotlin.KIExtensionByte
import cn.maizz.kotlin.extension.kotlin.KIExtensionString
import cn.maizz.kotlin.extension.kotlin.ranges.KIExtensionClosedRange

/**
 * 接入主类
 */
@Suppress("unused")
@Deprecated("即将废弃")
interface KIExtension :
        KIExtensionContext,
        KIExtensionView,
        KIExtensionTextView,
        KIExtensionEditView,
        KIExtensionFile,
        KIExtensionInputStream,
        KIExtensionString,
        KIExtensionClosedRange,
        KIExtensionList,
        KIExtensionDate,
        KIExtensionByte