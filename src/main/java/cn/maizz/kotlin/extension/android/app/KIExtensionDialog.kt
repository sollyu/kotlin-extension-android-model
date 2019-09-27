package cn.maizz.kotlin.extension.android.app

import android.app.Dialog
import android.view.View

/**
 * 一个自动关闭的dialog
 *
 * @param timeout 超时时间 单位毫秒
 */
fun Dialog.show(timeout: Long) {
    this@show.show()
    this.window!!.decorView.findViewById<View>(android.R.id.content).postDelayed({ this.dismiss() }, timeout)
}

/**
 * 一个自动关闭的Dialog
 *
 * @param timeout 超时时间 单位毫秒
 * @param callback 关闭后的回调 会复写setOnDismissListener
 */
fun Dialog.show(timeout: Long, callback: () -> Unit) {
    this@show.show()
    this.setOnDismissListener { callback() }
    this.window!!.decorView.findViewById<View>(android.R.id.content).postDelayed({ this.dismiss() }, timeout)
}
