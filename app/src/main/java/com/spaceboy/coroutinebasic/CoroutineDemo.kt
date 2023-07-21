package com.spaceboy.coroutinebasic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {

    GlobalScope.launch {
        launch {
            task2()
            println(Thread.currentThread().name)
        }
    }

    GlobalScope.launch {
        launch {
            task1()
            println(Thread.currentThread().name)
        }
    }


    Thread.sleep(2000L)
    println(Thread.currentThread().name)

}

suspend fun task1() {

    print("Hello ")

}

suspend fun task2() {
    print("World!")
}