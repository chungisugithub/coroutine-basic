package com.spaceboy.coroutinebasic

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class CoroutineDemoKtTest {
    @Test
    fun myFirstTest() = runBlocking {
        myOwnSuspendingFunc()
        assertEquals(10, 5 + 5)
    }
}

