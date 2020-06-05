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

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.app.usage.UsageStatsManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Toast
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * 查询手机中软件的使用情况
 *
 * @param intervalType
 * @param beginTime
 * @param endTime
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
fun Context.queryUsageStats(intervalType: Int = UsageStatsManager.INTERVAL_BEST, beginTime: Long = System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(10), endTime: Long = System.currentTimeMillis()) =
    (this.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager).queryUsageStats(intervalType, beginTime, endTime)

/**
 *
 */
fun Context.getTelephonyManager(): TelephonyManager = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

/**
 *
 */
fun Context.getSystemString(name: String, default: String = ""): String = Settings.System.getString(contentResolver, name) ?: default

/**
 * 打开一个已经存在的APP
 * @param packageName APP包名
 */
fun Context.launchAppByPackage(packageName: String): Unit = this.startActivity(this.packageManager.getLaunchIntentForPackage(packageName))

/**
 * 跳转到手机中存在的市场中
 * @param packageName APP包名
 */
fun Context.gotoMarket(packageName: String): Unit = this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")).apply { this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })

/**
 * 跳转到设置的使用软件详情
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.gotoUsageAccessSettings(): Unit = this.startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS).apply { this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })

/**
 * 跳转到软件的设置界面
 * @param packageName APP包名
 */
fun Context.gotoAppDetailsSettings(packageName: String): Unit = this.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply { this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); this.data = Uri.parse("package:$packageName") })

/**
 * 打开[无障碍]设置界面
 */
fun Context.gotoAccessibilitySettings(): Unit = this.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

/**
 * 跳转到手机默认桌面
 */
fun Context.gotoHome(): Unit = this.startActivity(Intent(Intent.ACTION_MAIN).apply { this.addCategory(Intent.CATEGORY_HOME) })

/**
 * 使用浏览器打开一个URL
 * @param url 要打开的网址
 */
fun Context.gotoUrl(url: String): Unit = this.startActivity(Intent.ACTION_VIEW, Uri.parse(url))

/**
 * 使用浏览器打开一个URL
 * @param uri 要打开的网址
 */
fun Context.gotoUrl(uri: Uri): Unit = this.startActivity(Intent.ACTION_VIEW, uri)

/**
 * 将一个apk文件安装
 *
 * @since 1.0.2
 */
fun Context.gotoApkInstaller(apkFile: File) {
    val intent: Intent = Intent()
    val apkUri: Uri = Uri.fromFile(apkFile)
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent.setDataAndType(apkUri, "application/vnd.android.package-archive")
    this.startActivity(intent)
}

/**
 * 弹出卸载app提示
 *
 * 需要 <font color=red>android.permission.REQUEST_DELETE_PACKAGES</font> 权限
 *
 * @since 1.0.2
 */
fun Context.showUninstallDialog(packageName: String): Unit = this.startActivity(Intent(Intent.ACTION_DELETE, Uri.fromParts("package", packageName, null)))

/**
 * 获取当前手机已经安装的应用列表
 *
 * @since 1.0.2
 */
fun Context.getInstalledApplications(flags: Int = 0): MutableList<ApplicationInfo> = this.packageManager.getInstalledApplications(flags)

/**
 * 获取当前手机已经安装的应用列表
 *
 * @since 1.0.2
 */
fun Context.getInstalledPackages(flags: Int = 0): List<PackageInfo> = this.packageManager.getInstalledPackages(flags)

/**
 * 判断软件是否安装
 */
fun Context.isPackageInstalled(packageName: String): Boolean = this.getInstalledApplications().any { it.packageName == packageName }

/**
 * 获取单独一个软件的详细信息
 */
fun Context.getApplicationInfo(packageName: String, flags: Int = 0): ApplicationInfo = this.packageManager.getApplicationInfo(packageName, flags)

/**
 * 获取已经安装的软件签名
 *
 * @since 1.0.2
 */
@Suppress(names = ["DEPRECATION"])
@SuppressLint(value = ["PackageManagerGetSignatures"])
fun Context.getPackageSignature(packageName: String): Array<Signature> = this.packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES).signatures

/**
 * 获取一个已经下载的APK文件的签名
 *
 * @since 1.0.0
 */
@Suppress(names = ["DEPRECATION"])
@SuppressLint(value = ["PackageManagerGetSignatures"])
fun Context.getPackageSignature(apkFile: File): Array<Signature>? = this.packageManager.getPackageArchiveInfo(apkFile.path, PackageManager.GET_SIGNATURES)?.signatures

/**
 * 获取剪贴板的内容
 */
fun Context.getClipboardString(): CharSequence? = (this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).primaryClip?.let { return@let if (it.itemCount > 0) it.getItemAt(0).text else null }

/**
 * 设置剪贴板的内容
 */
@Suppress(names = ["UsePropertyAccessSyntax"])
fun Context.setClipboardString(text: CharSequence, label: CharSequence? = null): Unit = (this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(ClipData.newPlainText(label, text))

/**
 * 设置剪贴板的内容
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
@Suppress(names = ["UsePropertyAccessSyntax"])
fun Context.setClipboardHtmlText(text: CharSequence, htmlText: String, label: CharSequence? = null): Unit = (this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(ClipData.newHtmlText(label, text, htmlText))

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
fun Context.sp2px(spValue: Float): Int = ((spValue - 0.5f) * resources.displayMetrics.scaledDensity).toInt()

/**
 * px转dp
 * @param px 像素值
 * @return dp
 */
fun Context.px2dp(pxValue: Float): Int = (pxValue / resources.displayMetrics.density + 0.5F).toInt()

/**
 * px转sp
 * @param pxValue 像素值
 * @return sp
 */
fun Context.px2sp(pxValue: Float): Int = (pxValue / resources.displayMetrics.scaledDensity + 0.5F).toInt()

/**
 * 显示一个Toast
 *
 * @param text 内容
 * @param duration 时常
 *
 * @see Toast.makeText
 */
fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_LONG): Unit = Toast.makeText(this, text, duration).show()

fun Context.startActivity(activity: Activity, vararg flag: Int = intArrayOf(Intent.FLAG_ACTIVITY_NEW_TASK)): Unit = this.startActivity(Intent(this, activity::class.java).apply { flag.forEach { this.addFlags(it) } })

fun Context.startActivity(action: String, uri: Uri, vararg flag: Int = intArrayOf(Intent.FLAG_ACTIVITY_NEW_TASK)): Unit = this.startActivity(Intent(action, uri).apply { flag.forEach { this.addFlags(it) } })

/**
 * 便捷获取DisplayMetrics对象
 */
fun Context.getMetrics(): DisplayMetrics {
    val windowManager: WindowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val displayMetrics: DisplayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics
}
