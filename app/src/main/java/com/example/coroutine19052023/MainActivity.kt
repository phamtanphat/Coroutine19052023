package com.example.coroutine19052023

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import java.io.IOException
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Coroutine context:
        // 1: Coroutine name => Tao ra name coroutine
        // 2: Dispatcher => Xu ly tac vu tren luong nao
        // 3: Job => Life cycle coroutine
        // 4: CoroutineExceptionHandler => Bat loi exception cua coroutine

        CoroutineScope(
            CoroutineName("Coroutine 1") +
                    Dispatchers.Main +
                    Job()
        ).launch {

            Log.d("BBB", "Parent" + Thread.currentThread().name)
            val exceptionHandler = CoroutineExceptionHandler { context, throwable ->
                Log.d("BBB", throwable.message.toString())
            }

            CoroutineScope(exceptionHandler).launch {
                Log.d("BBB", "Child" + Thread.currentThread().name)
                throw Exception("Error")
            }

            Log.d("BBB", "Tiep tuc")
        }
    }
}