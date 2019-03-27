package cn.maizz.kotlin.extension.android.app

import android.app.Dialog
import android.view.View

@Suppress("unused")
interface KIExtensionDialog {
    fun Dialog.show(timeout: Long) {
        this@show.show()
        this.window!!.decorView.findViewById<View>(android.R.id.content).postDelayed({ this.dismiss() }, timeout)
    }

    fun Dialog.show(timeout: Long, callback: () -> Unit) {
        this@show.show()
        this.setOnDismissListener { callback() }
        this.window!!.decorView.findViewById<View>(android.R.id.content).postDelayed({ this.dismiss() }, timeout)
    }
}