package com.example.data.executor

import com.example.domain.executor.ThreadExecutor
import io.reactivex.annotations.NonNull
import java.util.concurrent.*
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Job que permite ejecutar metodos en un hilo aparte.
 *
 * @version 1.0
 */

@Singleton
class JobExecutor @Inject constructor() : ThreadExecutor {
    private val threadPoolExecutor: ThreadPoolExecutor
    override fun execute(@NonNull runnable: Runnable) {
        threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0
        override fun newThread(@NonNull runnable: Runnable?): Thread {
            return Thread(
                runnable,
                THREAD_NAME + counter++
            )
        }

        companion object {
            const val THREAD_NAME = "android_"
        }
    }

    companion object {
        private const val INITIAL_POOL_SIZE: Int = 3
        private const val MAX_POOL_SIZE: Int = 5
        private const val KEEP_ALIVE_TIME: Long = 10
        private val KEEP_ALIVE_TIME_UNIT: TimeUnit = TimeUnit.SECONDS
    }

    init {
        val workQueue: BlockingQueue<Runnable> = LinkedBlockingQueue()
        threadPoolExecutor = ThreadPoolExecutor(
            INITIAL_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            KEEP_ALIVE_TIME_UNIT,
            workQueue,
            JobThreadFactory()
        )
    }
}