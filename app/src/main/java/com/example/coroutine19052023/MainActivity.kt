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

//        val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
//
//        }
        CoroutineScope(
            CoroutineName("Coroutine 1") +
                    Dispatchers.IO +
                    Job()
//                    coroutineExceptionHandler
        ).launch {

            val deferred1 = async { getRandomNumber() }

            val deferred2 = async { getRandomNumber() }

            val number1 = deferred1.await()
            val number2 = deferred2.await()
            Log.d("BBB", (number1 + number2).toString())
        }
    }

    suspend fun getRandomNumber(): Int {
        delay(2000)
        return Random.nextInt(100)
    }
}