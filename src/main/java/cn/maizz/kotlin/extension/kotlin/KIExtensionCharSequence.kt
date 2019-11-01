package cn.maizz.kotlin.extension.kotlin

/**
 * 使用正则进行查找
 *
 * @see Regex.find
 */
fun CharSequence.find(regex: Regex, startIndex: Int = 0): MatchResult? = regex.find(input = this, startIndex = startIndex)

/**
 * 使用正则进行查找全部
 *
 * @see Regex.findAll
 */
fun CharSequence.findAll(regex: Regex, startIndex: Int = 0): Sequence<MatchResult> = regex.findAll(input = this, startIndex = startIndex)