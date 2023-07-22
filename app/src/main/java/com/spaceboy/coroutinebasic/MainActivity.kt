package com.spaceboy.coroutinebasic

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spaceboy.coroutinebasic.ui.theme.CoroutineBasicTheme
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    val scope = CoroutineScope(CoroutineName("MyScope"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CoroutineBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LaunchedEffect(key1 = true) {
//                        scope.launch {
//                            Log.d("CoroutineName", this.coroutineContext.toString())
//                            Log.d("CoroutineName", Thread.currentThread().name)
//                        }
                        /*
                        https://stackoverflow.com/questions/59368838/difference-between-coroutinescope-and-coroutinescope-in-kotlin
                        CoroutineScope vs. coroutineScope
                        Unstructured vs. Structured
                         */
//                        val result = downloadUserData1() // Output : Result : 0
                        val result = downloadUserData2() // Output : Result : 100
                        Toast.makeText(applicationContext, "Result : $result", Toast.LENGTH_LONG).show()

                    }

                    Greeting("Android")
                }
            }
        }
    }

    //Unstructured Concurrency
    private suspend fun downloadUserData1(): Int {
        var result = 0
        // Here, we use CoroutineScope (Capital C version) which will start a new scope and
        // launch coroutine in new scope Dispatchers.IO, Not In Parent Scope which is Dispatchers.Main
        // Thus, this function would directly return without waiting for loop completion and will return 0
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0 until 100) {
                kotlinx.coroutines.delay(10)
                result++
            }
        }
        return result
        //Output : Result : 0
    }

    //Structured Concurrency (Recommended)
    private suspend fun downloadUserData2(): Int {
        var result = 0
        // By using coroutineScope (Smaller c version) below, we ensure that this coroutine would execute in the
        // parent/caller coroutine's scope, so it would make sure that the for loop would complete
        // before returning from this suspended function. This will return 20000 properly
        coroutineScope {
            for (i in 0 until 100) {
                kotlinx.coroutines.delay(10)
                result++
            }
        }
        return result
        //Output : Result : 100
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoroutineBasicTheme {
        Greeting("Android")
    }
}