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

package cn.maizz.kotlin.extension.android.graphics

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer


/**
 * bitmap转成字节数组字节存储顺序为ARGB
 */
fun Bitmap.convertToByteArray(): ByteArray {
    val size: Int = this.byteCount
    val byteBuffer: ByteBuffer = ByteBuffer.allocate(size)
    val bytes = ByteArray(size)
    this.copyPixelsToBuffer(byteBuffer)
    byteBuffer.rewind()
    byteBuffer.get(bytes)
    return bytes
}

/**
 * bitmap 字节长度
 */
fun Bitmap.length(): Int = convertToByteArray().size

/**
 * bitmap翻转
 */
fun Bitmap.flipping(): Bitmap = Bitmap.createBitmap(this, 0, 0, this.width, this.height, Matrix().apply { postScale(1.0f, -1.0f) }, true)

/**
 * bitmap 旋转
 */
fun Bitmap.rotate(degrees: Float = 0f): Bitmap = Bitmap.createBitmap(this, 0, 0, this.width, this.height, Matrix().apply { postRotate(degrees) }, true)

/**
 * bitmap旋转
 * degrees 旋转角度
 * px 旋转中心x坐标
 * py 旋转中心Y坐标
 */
fun Bitmap.rotate(degrees: Float, px: Float, py: Float): Bitmap = Bitmap.createBitmap(this, 0, 0, this.width, this.height, Matrix().apply { postRotate(degrees, 0f, 0f) }, true)

/**
 * 进行缩放
 *
 * @param px x轴缩放比例
 * @param py y轴缩放比例
 */
fun Bitmap.zoom(px: Float, py: Float): Bitmap = Bitmap.createBitmap(this, 0, 0, this.width, this.height, Matrix().apply { postScale(px, py) }, true)

fun Bitmap.zoomByWidthAndHeight(width: Int, height: Int): Bitmap = zoom(((width.toFloat() / this.width.toFloat())), (height.toFloat() / this.height))
fun Bitmap.resize(newWidth: Int, newHeight: Int): Bitmap = this.zoomByWidthAndHeight(newWidth, newHeight)

/**
 * 保存图片
 */
fun Bitmap.save(file: File, compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG, quality: Int = 100): Boolean = compress(compressFormat, quality, FileOutputStream(file))

/**
 * 格式转换
 */
fun Bitmap.convert(compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG, quality: Int = 100): Bitmap {
    val byteArrayOutputStream = ByteArrayOutputStream()
    this.compress(compressFormat, quality, byteArrayOutputStream)
    return BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size())
}

fun Bitmap.crop(cropWidth: Int): Bitmap {
    return Bitmap.createBitmap(this, (this.width - cropWidth) / 2, (this.height - cropWidth) / 2, cropWidth, cropWidth)
}

