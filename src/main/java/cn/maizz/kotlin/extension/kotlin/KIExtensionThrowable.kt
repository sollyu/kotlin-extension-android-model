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

import java.util.*

/**
 * 异常信息国际化
 */

/**
 * 调用国际化语言
 *
 * 备注：
 *
 * 国际化使用ResourceBundle实现
 * 每个异常种类对应一种展示，就比如： java.io.FileNotFoundException=\u53D6\u6D88 表示文件未找到时的错误提示
 * ResourceBundle文件存在于jar或apk的/res/raw/i18n_throwable
 *
 * 如果未找到将使用原始的内容
 *
 * @param locale   对应语言
 * @param baseName 加载对应的Bundle
 */
fun Throwable.i18nMessage(locale: Locale = Locale.getDefault(), baseName: String = "res.raw.i18n_throwable"): String? = getI18nMessage(locale, baseName)

fun Throwable.getI18nMessage(locale: Locale = Locale.getDefault(), baseName: String = "res.raw.i18n_throwable"): String? = try {
    getI18nMessage(ResourceBundle.getBundle(baseName, locale))
} catch (t: Throwable) {
    this.localizedMessage
}


/**
 * 使用自己的Bundle进行加载
 *
 * @param resourceBundle bundle
 */
fun Throwable.getI18nMessage(resourceBundle: ResourceBundle): String? = if (resourceBundle.containsKey(this.javaClass.name)) resourceBundle.getString(this.javaClass.name) else this.localizedMessage


