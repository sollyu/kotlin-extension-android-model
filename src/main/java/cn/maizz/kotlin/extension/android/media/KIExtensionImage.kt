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
