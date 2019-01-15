package cn.maizz.kotlin.library.queue

abstract class QueueRetry(val retryTimesMax: Int = 5) : QueueBase() {
    var retryTimesCurrent: Int = 0
}