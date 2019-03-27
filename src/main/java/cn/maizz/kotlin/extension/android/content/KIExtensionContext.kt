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

package cn.maizz.kotlin.extension.android.content

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.usage.UsageStatsManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import java.io.File
import java.util.concurrent.TimeUnit

@Suppress("HasPlatformType", "unused")
interface KIExtensionContext {

    /**
     * 查询手机中软件的使用情况
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    fun Context.queryUsageStats(intervalType: Int = UsageStatsManager.INTERVAL_BEST, beginTime: Long = System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(10), endTime: Long = System.currentTimeMillis()) =
        (this.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager).queryUsageStats(intervalType, beginTime, endTime)

    fun Context.getTelephonyManager(): TelephonyManager = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    fun Context.getSystemString(name: String, default: String = ""): String = Settings.System.getString(contentResolver, name) ?: default

    /**
     * 打开一个已经存在的APP
     * @param packageName APP包名
     */
    fun Context.launchAppByPackage(packageName: String) = this.startActivity(this.packageManager.getLaunchIntentForPackage(packageName))

    /**
     * 跳转到手机中存在的市场中
     * @param packageName APP包名
     */
    fun Context.gotoMarket(packageName: String) = this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")).apply { this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })

    /**
     * 跳转到设置的使用软件详情
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun Context.gotoUsageAccessSettings() = this.startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS).apply { this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })

    /**
     * 跳转到软件的设置界面
     * @param packageName APP包名
     */
    fun Context.gotoAppDetailsSettings(packageName: String) = this.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply { this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); this.data = Uri.parse("package:$packageName") })

    /**
     * 跳转到手机默认桌面
     */
    fun Context.gotoHome() = this.startActivity(Intent(Intent.ACTION_MAIN).apply { this.addCategory(Intent.CATEGORY_HOME) })

    /**
     * 使用浏览器打开一个URL
     * @param url 要打开的网址
     */
    fun Context.gotoUrl(url: String) = this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

    /**
     * 使用浏览器打开一个URL
     * @param url 要打开的网址
     */
    fun Context.gotoUrl(uri: Uri) = this.startActivity(Intent(Intent.ACTION_VIEW, uri))

    /**
     * 获取当前手机已经安装的应用列表
     */
    fun Context.installedPackageList(): MutableList<ApplicationInfo> = this.packageManager.getInstalledApplications(0)!!

    /**
     * 判断软件是否安装
     */
    fun Context.isPackageInstalled(packageName: String): Boolean = this.installedPackageList().any { it.packageName == packageName }

    /**
     * 获取单独一个软件的详细信息
     */
    fun Context.getApplicationInfo(packageName: String, flags: Int = 0): ApplicationInfo = this.packageManager.getApplicationInfo(packageName, flags)


    /**
     * 获取已经安装的软件签名
     */
    @Suppress("DEPRECATION")
    @SuppressLint("PackageManagerGetSignatures")
    fun Context.getApplicationSignature(packageName: String): Signature = this.packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES).signatures[0]

    /**
     * 获取一个已经下载的APK文件的签名
     */
    @Suppress("DEPRECATION")
    @SuppressLint("PackageManagerGetSignatures")
    fun Context.getPackageSignature(apkFile: File): Signature = this.packageManager.getPackageArchiveInfo(apkFile.path, PackageManager.GET_SIGNATURES).signatures[0]

    /**
     * 获取剪贴板的内容
     */
    fun Context.getClipboardString() = (this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).primaryClip?.let { return@let if (it.itemCount > 0) it.getItemAt(0).text else null }

    /**
     * 设置剪贴板的内容
     */
    @Suppress("UsePropertyAccessSyntax")
    fun Context.setClipboardString(text: CharSequence, label: CharSequence? = null) = (this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(ClipData.newPlainText(label, text))

    /**
     * 设置剪贴板的内容
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Suppress("UsePropertyAccessSyntax")
    fun Context.setClipboardHtmlText(text: CharSequence, htmlText: String, label: CharSequence? = null) = (this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(ClipData.newHtmlText(label, text, htmlText))

    /**
     * dp转换成px
     * @param dpValue dp 值
     * @return dp 转换成px 的数值 例子： 输入的dpValue 为10  density 为3 则返回的值为30px
     */
    fun Context.dp2px(dpValue: Int): Int = (resources.displayMetrics.density * dpValue + 0.5F).toInt()

    /**
     * sp转乘px
     * @param spValue sp值
     * @return sp转px 的数值
     */
    fun Context.sp2px(spValue: Float) = ((spValue - 0.5f) * resources.displayMetrics.scaledDensity).toInt()

    /**
     * px转dp
     * @param px 像素值
     * @return dp
     */
    fun Context.px2dp(pxValue: Float) = (pxValue / resources.displayMetrics.density + 0.5F).toInt()

    /**
     * px转sp
     * @param pxValue 像素值
     * @return sp
     */
    fun Context.px2sp(pxValue: Float) = (pxValue / resources.displayMetrics.scaledDensity + 0.5F).toInt()
}