package com.example.yourhealthlog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val backstack = rememberNavBackStack(HomeKey)
            NavDisplay (
                backstack,
                onBack = {backstack.removeLastOrNull()},
                entryProvider = { key ->
                    when(key)
                        {
                            is HomeKey -> NavEntry(key) {Text("Home")}
                            else -> NavEntry(UnknownKey) {Text(text = "unknown")}
                        }
                }
            )
        }
    }
}
        