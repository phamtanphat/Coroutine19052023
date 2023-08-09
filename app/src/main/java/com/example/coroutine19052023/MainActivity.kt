package com.example.coroutine19052023

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Coroutine context:
        // 1: Coroutine name => Tao ra name coroutine
        // 2: Dispatcher => Xu ly tac vu tren luong nao
        // 3: Job => Life cycle coroutine
        // 4: CoroutineExceptionHandler => Bat loi exception cua coroutine

        val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->

        }

        CoroutineScope(
            CoroutineName("Coroutine 1") +
                    Dispatchers.IO +
                    Job() +
                    coroutineExceptionHandler
        ).launch {
            supervisorScope {
                // launch the first child -- its exception is ignored for this example (don't do this in practice!)
                val firstChild = launch(CoroutineExceptionHandler { _, _ ->  }) {
                    Log.d("BBB", "First child is failing")
                    throw AssertionError("First child is cancelled")
                }
                // launch the second child
                val secondChild = launch {
                    firstChild.join()
                    // Cancellation of the first child is not propagated to the second child
                    Log.d("BBB", "First child is cancelled: ${firstChild.isCancelled}, but second one is still active")
                    try {
                        delay(Long.MAX_VALUE)
                    } finally {
                        // But cancellation of the supervisor is propagated
                        Log.d("BBB", "Second child is cancelled because supervisor is cancelled")
                    }
                }
                // wait until the first child fails & completes
                firstChild.join()
                secondChild.join()
            }
        }
    }
}