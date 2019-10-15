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

package cn.maizz.kotlin.extension.android.content

import android.content.SharedPreferences
import org.json.JSONObject

/**
 * 判断配置是否存在
 */
fun SharedPreferences.has(key: String): Boolean = !this.getString(key, null).isNullOrEmpty()

/**
 * 直接获取一个Json对象
 */
fun SharedPreferences.getJson(key: String, default: String = "{}"): JSONObject = JSONObject(this.getString(key, default) ?: default)
