package cn.maizz.kotlin.extension.android.content

import android.content.SharedPreferences
import org.json.JSONObject

interface KIExtensionSharedPreferences {

    /**
     * 判断配置是否存在
     */
    fun SharedPreferences.has(key: String):Boolean = !this.getString(key, null).isNullOrEmpty()

    /**
     * 直接获取一个Json对象
     */
    fun SharedPreferences.getJson(key: String, default: String = "{}"): JSONObject = JSONObject(this.getString(key, default))
}