package cn.maizz.kotlin.extension.kotlin

import java.util.*
import kotlin.collections.ArrayList

fun <T> List<T>.toArrayList(): ArrayList<T> = ArrayList(this)

fun <T> List<T>.toLinkedList(): LinkedList<T> = LinkedList(this)