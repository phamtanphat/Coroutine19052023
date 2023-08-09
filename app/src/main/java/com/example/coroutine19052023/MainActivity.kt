package com.example.coroutine19052023

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
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

            val jobChildren1 = launch {
                delay(500)
                Log.d("BBB", "Children 1 ${Thread.currentThread().name}")
            }

            val jobChildren2 = launch {
                delay(200)
                Log.d("BBB", "Children 2 ${Thread.currentThread().name}")
            }

            Log.d("BBB", "Finish")
        }

    }
}