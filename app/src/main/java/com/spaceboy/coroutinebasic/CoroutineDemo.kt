package com.spaceboy.coroutinebasic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {

    repeat(50) {
        CoroutineScope(Dispatchers.IO).launch {
            println(Thread.currentThread().name)
        }
    }

    Thread.sleep(1000L)
}

