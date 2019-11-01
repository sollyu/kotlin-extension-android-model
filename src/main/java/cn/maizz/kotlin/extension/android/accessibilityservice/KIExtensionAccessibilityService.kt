package cn.maizz.kotlin.extension.android.accessibilityservice

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.graphics.Point
import android.os.Build
import android.os.Handler
import androidx.annotation.RequiresApi

/**
 * 发送返回事件
 *
 * @see android.accessibilityservice.AccessibilityService.performGlobalAction
 * @see android.accessibilityservice.AccessibilityService.GLOBAL_ACTION_BACK
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
fun AccessibilityService.performActionBack(): Boolean = this.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)


/**
 * 模拟滑动
 *
 * @see android.graphics.Path.Path()
 * @see android.accessibilityservice.AccessibilityService.dispatchGesture
 * @see android.accessibilityservice.GestureDescription.StrokeDescription
 */
@RequiresApi(Build.VERSION_CODES.N)
fun AccessibilityService.slide(start: Point, end: Point, time: Long = 200, duration: Long = 200, callback: AccessibilityService.GestureResultCallback? = null, handler: Handler? = null): Boolean {
    val path: Path = Path()
    path.moveTo(start.x.toFloat(), start.y.toFloat())
    path.lineTo(end.x.toFloat(), end.y.toFloat())

    return this.dispatchGesture(GestureDescription.Builder().addStroke(GestureDescription.StrokeDescription(path, time, duration)).build(), callback, handler)
}
