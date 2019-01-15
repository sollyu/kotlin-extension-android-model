package cn.maizz.kotlin.library.queue

import java.util.concurrent.Future

/**
 *
 */
abstract class QueueBase {

    /**
     * 当前操作是否完成，如为false则一直等待
     */
    open var isFinish:Boolean = false

    /**
     * 是否需要重新执行
     *
     * @see onRun
     */
    open var reRun: Boolean = false

    /**
     * 添加任务时所被执行的函数
     *
     * @param future 放入线程池中的对象
     */
    open fun onSubscribe(future: Future<*>) {

    }

    /**
     * 执行的核心函数
     * 备注：如果onRun函数为同步函数，请在函数的末尾将isFinish设置为true，否则将会一直卡住
     *
     * @param queueBase 当前对象
     * @see isFinish
     */
    open fun onRun(queueBase: QueueBase) {

    }

    /**
     * 在执行#onRun时如果抛出异常，将执行此函数
     * 请注意，这里并没有捕获任何的异常，请自行处理，应该尽量避免反复回调
     * 如果需要重新执行onRun方法，只需设置reRun=true即可
     *
     * @param throwable 异常信息
     * @see onRun
     * @see reRun
     */
    open fun onError(throwable: Throwable) {
    }

    /**
     * 在执行任务即将结束时执行此方法
     */
    open fun onComplete() {
    }

}