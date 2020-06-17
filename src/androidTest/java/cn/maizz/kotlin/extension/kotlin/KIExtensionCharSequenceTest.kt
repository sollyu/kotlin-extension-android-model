package cn.maizz.kotlin.extension.kotlin

import org.junit.Assert
import org.junit.Test

class KIExtensionCharSequenceTest {

    @Test
    fun find() {
        Assert.assertEquals("123".find(pattern = "(\\d+)")?.groupValues?.getOrNull(index = 1), "123")
        Assert.assertEquals("123".find(regex = Regex("(\\d+)"))?.groupValues?.getOrNull(index = 1), "123")
    }

    @Test
    fun findFirst() {
        Assert.assertEquals("123".findFirst(pattern = "(\\d+)"), "123")
    }
}