package cn.maizz.kotlin.extension.android.view.accessibility

import android.os.Build
import android.os.Bundle
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import java.util.concurrent.TimeUnit

/**
 * 根据ContentDescription进行查找
 */
fun AccessibilityNodeInfo.findAccessibilityNodeInfosByContentDescription(contentDescription: String): List<AccessibilityNodeInfo> = this.all().filter { it.contentDescription == contentDescription }

/**
 * 根据ClassName进行查找
 */
fun AccessibilityNodeInfo.findAccessibilityNodeInfosByClassName(className: String): List<AccessibilityNodeInfo> = all().filter { it.className == className }

/**
 * 获取所有节点信息
 *
 * @param includeTree 是否包含树节点，就是非末尾节点
 */
fun AccessibilityNodeInfo.all(includeTree: Boolean = true): ArrayList<AccessibilityNodeInfo> {
    val accessibilityNodeInfoList: ArrayList<AccessibilityNodeInfo> = arrayListOf()
    this.all(includeTree = includeTree, accessibilityNodeInfo = this, arrayList = accessibilityNodeInfoList)
    return accessibilityNodeInfoList
}

/**
 * 获取所有几点信息[递归]
 */
fun AccessibilityNodeInfo.all(includeTree: Boolean = true, accessibilityNodeInfo: AccessibilityNodeInfo, arrayList: ArrayList<AccessibilityNodeInfo>) {
    if (includeTree || accessibilityNodeInfo.childCount == 0)
        arrayList.add(accessibilityNodeInfo)
    for (i: Int in 0 until accessibilityNodeInfo.childCount)
        this.all(includeTree = includeTree, accessibilityNodeInfo = accessibilityNodeInfo.getChild(i), arrayList = arrayList)
}

/**
 * 提供一个forEach
 */
fun AccessibilityNodeInfo.forEach(action: (AccessibilityNodeInfo) -> Unit): Unit {
    for (i: Int in 0 until this.childCount)
        action(this.getChild(i))
}

/**
 *
 */
fun AccessibilityNodeInfo.first(): AccessibilityNodeInfo = this.getChild(0)

/**
 *
 */
fun AccessibilityNodeInfo.firstOrNull(): AccessibilityNodeInfo? = if (this.childCount == 0) null else this.getChild(0)

/**
 *
 */
fun AccessibilityNodeInfo.first(predicate: (AccessibilityNodeInfo) -> Boolean): AccessibilityNodeInfo {
    for (i: Int in 0 until this.childCount) if (predicate(this.getChild(i))) return this.getChild(i)
    throw NoSuchElementException("Collection contains no element matching the predicate.")
}

/**
 *
 */
fun AccessibilityNodeInfo.firstOrNull(predicate: (AccessibilityNodeInfo) -> Boolean): AccessibilityNodeInfo? {
    for (i: Int in 0 until this.childCount) if (predicate(this.getChild(i))) return this.getChild(i)
    return null
}

/**
 * 提供过滤函数
 * 此函数只过滤当前节点下第一个元素
 * 如需要全部请使用@all方法
 *
 * @see all
 */
fun AccessibilityNodeInfo.filter(predicate: (AccessibilityNodeInfo) -> Boolean): List<AccessibilityNodeInfo> {
    val arrayList: ArrayList<AccessibilityNodeInfo> = arrayListOf()
    for (i: Int in 0 until this.childCount) if (predicate(this.getChild(i))) arrayList.add(this.getChild(i))
    return arrayList
}

/**
 * 使用循环的方法进行查找控件
 * 可以理解为等待一个控件出现
 *
 * @param times 循环次数
 * @param duration 单次等待时长
 * @param timeUnit 单次等待时长单位
 * @param predicate 查找条件
 *
 * @return
 */
fun AccessibilityNodeInfo.findAccessibilityNodeInfos(times: Int = 10, duration: Long = 3, timeUnit: TimeUnit = TimeUnit.SECONDS, predicate: (AccessibilityNodeInfo) -> Boolean): List<AccessibilityNodeInfo> {

    var accessibilityNodeInfo: List<AccessibilityNodeInfo> = this.all().filter(predicate = predicate)
    for (i: Int in 0 until times) {
        if (accessibilityNodeInfo.isNotEmpty())
            return accessibilityNodeInfo

        Thread.sleep(timeUnit.toMillis(duration))
        accessibilityNodeInfo = this.all().filter(predicate = predicate)
    }

    return accessibilityNodeInfo

}


/**
 * 发送点击事件
 *
 * @see android.view.accessibility.AccessibilityNodeInfo.performAction(int)
 * @see android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK
 */
@androidx.annotation.RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
fun AccessibilityNodeInfo.performActionClick(): Boolean = this.performAction(AccessibilityNodeInfo.ACTION_CLICK)

/**
 * 发送点击事件
 * 如本身没有点击事件
 * 自动往上层寻找
 * 最高寻找5层
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
fun AccessibilityNodeInfo.performActionClickAuto(): Boolean {
    var canClickAccessibilityNodeInfo: AccessibilityNodeInfo = this
    for (i: Int in 0..5) {
        if (canClickAccessibilityNodeInfo.isClickable)
            return canClickAccessibilityNodeInfo.performActionClick()
        canClickAccessibilityNodeInfo = canClickAccessibilityNodeInfo.parent
    }
    return false
}

/**
 * 设置文本
 *
 * @see android.view.accessibility.AccessibilityNodeInfo.performAction(int, android.os.Bundle)
 * @see android.view.accessibility.AccessibilityNodeInfo.ACTION_SET_TEXT
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun AccessibilityNodeInfo.performActionSetText(text: String?): Boolean = this.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, Bundle().apply { this.putCharSequence(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text) })
