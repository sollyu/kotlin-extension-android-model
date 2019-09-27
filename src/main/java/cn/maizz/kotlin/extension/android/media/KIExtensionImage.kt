package cn.maizz.kotlin.extension.android.media

import android.annotation.TargetApi
import android.graphics.Bitmap
import android.media.Image
import android.os.Build
import java.nio.ByteBuffer


@TargetApi(Build.VERSION_CODES.KITKAT)
fun Image.toBitmap(): Bitmap {
    val imagePlane: Image.Plane = planes[0]
    val byteBuffer: ByteBuffer = imagePlane.buffer
    byteBuffer.position(0)

    val pixelStride: Int = imagePlane.pixelStride
    val bitmapWidth: Int = imagePlane.rowStride - width * pixelStride
    val v0 = Bitmap.createBitmap(width + bitmapWidth / pixelStride, height, Bitmap.Config.ARGB_8888)

    v0.copyPixelsFromBuffer(byteBuffer)

    return if (bitmapWidth != 0) Bitmap.createBitmap(v0, 0, 0, width, height) else v0
}
