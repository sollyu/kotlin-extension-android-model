package cn.maizz.kotlin.extension.kotlin

import cn.maizz.kotlin.extension.java.io.writeLines
import java.io.File
import java.nio.charset.Charset
import java.util.*
import kotlin.collections.ArrayList

fun <T> List<T>.toArrayList(): ArrayList<T> = ArrayList(this)

fun <T> List<T>.toLinkedList(): LinkedList<T> = LinkedList(this)

/**
 * @see File.writeLines
 */
fun <T> List<T>.write(file: File, encoding: Charset = Charsets.UTF_8):Unit = file.writeLines(collection = this, encoding = encoding)