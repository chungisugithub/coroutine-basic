package com.spaceboy.coroutinebasic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

fun main() {
    GlobalScope.launch {
        task2()
    }
    task1()
    Thread.sleep(2000L)
}

fun task1() {
    print("Hello ")
}

suspend fun task2() {
    sleep(1000L)
    print("World!")
}