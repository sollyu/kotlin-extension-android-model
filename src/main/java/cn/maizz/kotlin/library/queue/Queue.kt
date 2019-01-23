package cn.maizz.kotlin.library.queue

import cn.maizz.kotlin.extension.java.util.KIExtensionDate
import java.util.*
import java.util.concurrent.*

/**
 * 线程队列
 *
 * 整个QueueBase是否执行结束取决于QueueBase.isFinish
 * 请在合适的位置设置，这是必须的一步
 *
 * @param executorService 线程池的类型
 * @see QueueBase.isFinish
 * @see Executors
 */
@Suppress("MemberVisibilityCanBePrivate", "unused")
class Queue(private val executorService: ExecutorService = Executors.newScheduledThreadPool(3)): KIExtensionDate {

    /**
     * 添加一个任务到队列
     *
     * @param block 回调函数
     * @see QueueBase.isFinish
     * @see ExecutorService.submit
     */
    fun submit(block: (queueBase: QueueBase) -> Unit): Future<*> {
        return submit(object : QueueBase() {
            override fun onRun(queueBase: QueueBase) {
                super.onRun(queueBase)
                block(queueBase)
            }
        })
    }

    /**
     * 添加一个任务到队列
     *
     * @param queueBase 回调函数
     * @see QueueBase.isFinish
     * @see ExecutorService.submit
     */
    fun submit(queueBase: QueueBase): Future<*> {
        val future: Future<*> = executorService.submit(QueueRunnable(queueBase))
        queueBase.onSubscribe(future)
        return future
    }

    /**
     * 定时一个任务在指定时间执行
     * @param date              具体执行的时间
     * @param queueInterface    回调函数
     * @return 可能为空
     * @see ScheduledExecutorService.schedule
     */
    fun schedule(date: Date, queueInterface: (queueInterface: QueueBase) -> Unit): ScheduledFuture<*>? {
        return schedule(object : QueueBase() {
            override fun onRun(queueBase: QueueBase) {
                super.onRun(queueBase)
                queueInterface(queueBase)
            }
        }, TimeUnit.MILLISECONDS.toSeconds(date.time - System.currentTimeMillis()), TimeUnit.SECONDS)
    }

    /**
     * 添加一个任务，在之后的一个时间运行
     * @param delay 根据添加时间延时多长时间后执行
     * @param unit  添加时间的单位
     * @param block 回调函数
     * @see ScheduledExecutorService.schedule
     */
    fun schedule(delay: Long, unit: TimeUnit, block: (queueBase: QueueBase) -> Unit): ScheduledFuture<*>? {
        return schedule(object : QueueBase() {
            override fun onRun(queueBase: QueueBase) {
                super.onRun(queueBase)
                block(queueBase)
            }
        }, delay, unit)
    }

    /**
     * 添加一个任务，在之后的一个时间运行
     *
     * @param queueBase 回调函数
     * @param delay     根据添加时间延时多长时间后执行
     * @param unit      添加时间的单位
     * @see ScheduledExecutorService.schedule
     */
    fun schedule(queueBase: QueueBase, delay: Long, unit: TimeUnit): ScheduledFuture<*>? {
        if (executorService is ScheduledExecutorService) {
            val future: ScheduledFuture<*> = executorService.schedule(QueueRunnable(queueBase), delay, unit)
            queueBase.onSubscribe(future)
            return future
        }
        return null
    }

    /**
     * 创建一个支持周期性的任务
     * 备注：请在合适的位置设置isFinish
     *
     * 只有executorService为ScheduledExecutorService类型时才会被成功调用
     * @param initialDelay  第一次执行时的延时时长
     * @param period        后面执行的间隔
     * @param unit          间隔时间类型
     * @param block         回调函数
     * @see QueueBase.isFinish
     * @see ScheduledExecutorService.scheduleAtFixedRate
     */
    fun scheduleAtFixedRate(initialDelay: Long, period: Long, unit: TimeUnit, block: (queueBase: QueueBase) -> Unit): ScheduledFuture<*>? {
        return scheduleAtFixedRate(object : QueueBase() {
            override fun onRun(queueBase: QueueBase) {
                super.onRun(queueBase)
                block(queueBase)
            }
        }, initialDelay, period, unit)
    }

    /**
     * 创建一个支持周期性的任务(固定时间执行)
     *
     * 备注：请在合适的位置设置isFinish
     * 只有executorService为ScheduledExecutorService类型时才会被成功调用
     *
     * @param queueInterface 回调函数
     * @param initialDelay   第一次执行时的延时时长
     * @param period         后面执行的间隔
     * @param unit           间隔时间类型
     * @see QueueBase.isFinish
     * @see ScheduledExecutorService.scheduleAtFixedRate
     */
    fun scheduleAtFixedRate(queueInterface: QueueBase, initialDelay: Long, period: Long, unit: TimeUnit): ScheduledFuture<*>? {
        if (executorService is ScheduledExecutorService) {
            val future: ScheduledFuture<*> = executorService.scheduleAtFixedRate(QueueRunnable(queueInterface), initialDelay, period, unit)
            queueInterface.onSubscribe(future)
            return future
        }
        return null
    }

    /**
     * 创建一个支持周期性的任务(根据最后一次执行时间)
     *
     * 备注：请在合适的位置设置isFinish
     * 只有executorService为ScheduledExecutorService类型时才会被成功调用
     *
     * @param initialDelay  第一次执行时的延时时长
     * @param period        后面执行的间隔
     * @param unit          间隔时间类型
     * @param block         回调函数
     * @see QueueBase.isFinish
     * @see ScheduledExecutorService.scheduleWithFixedDelay
     */
    fun scheduleWithFixedDelay(initialDelay: Long, period: Long, unit: TimeUnit, block: (queueInterface: QueueBase) -> Unit): ScheduledFuture<*>? {
        return scheduleWithFixedDelay(object : QueueBase() {
            override fun onRun(queueBase: QueueBase) {
                super.onRun(queueBase)
                block(queueBase)
            }
        }, initialDelay, period, unit)
    }

    /**
     * 创建一个支持周期性的任务(根据最后一次执行时间)
     *
     * 备注：请在合适的位置设置isFinish
     * 只有executorService为ScheduledExecutorService类型时才会被成功调用
     *
     * @param initialDelay  第一次执行时的延时时长
     * @param period        后面执行的间隔
     * @param unit          间隔时间类型
     * @param queueBase     回调函数
     * @see QueueBase.isFinish
     * @see ScheduledExecutorService.scheduleWithFixedDelay
     */
    fun scheduleWithFixedDelay(queueBase: QueueBase, initialDelay: Long, period: Long, unit: TimeUnit): ScheduledFuture<*>? {
        if (executorService is ScheduledExecutorService) {
            val future: ScheduledFuture<*> = executorService.scheduleWithFixedDelay(QueueRunnable(queueBase), initialDelay, period, unit)
            queueBase.onSubscribe(future)
            return future
        }
        return null
    }

    /**
     * 将线程池状态置为 shutdown ,并不会立即停止
     *
     * 备注：只是关闭了提交通道，用submit()是无效的；而内部该怎么跑还是怎么跑，跑完再停。
     */
    fun shutdown() = executorService.shutdown()

    /**
     * 将线程池状态置为stop，企图立即停止，事实上不一定
     *
     * 备注：能立即停止线程池，正在跑的和正在等待的任务都停下了。
     *
     * @see ExecutorService.shutdownNow
     */
    fun shutdownNow() = executorService.shutdownNow()

    /**
     * 是否shutdown
     *
     * @see shutdown
     * @see shutdownNow
     * @see ExecutorService.isShutdown
     */
    fun isShutdown() = executorService.isShutdown

    /**
     * 若关闭后所有任务都已完成，则返回true。
     *
     * 备注：注意除非首先调用shutdown或shutdownNow，否则isTerminated永不为true。
     *
     * @return 若关闭后所有任务都已完成，则返回true。
     * @see ExecutorService.isTerminated
     */
    fun isTerminated() = executorService.isTerminated

    /**
     * 当前线程阻塞
     *
     * @see ExecutorService.awaitTermination
     */
    fun awaitTermination(timeout:Long, unit:TimeUnit) = executorService.awaitTermination(timeout, unit)

    /**
     * 内部代理类
     */
    private inner class QueueRunnable(private val queueBase: QueueBase) : Runnable {

        @Synchronized
        override fun run() {
            do {
                try {
                    queueBase.isFinish = false
                    queueBase.onRun(queueBase)

                    /* 此处使用无限循环等待任务结束信号 */
                    while (queueBase.isFinish == false) {
                        Thread.sleep(100)
                    }
                } catch (t: Throwable) {
                    queueBase.onError(t)
                }

                if (queueBase.reRun == false) {
                    break
                }
            } while (true)

            queueBase.onComplete()
        }
    }
}