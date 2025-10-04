package com.example.yourhealthlog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.yourhealthlog.components.CustomNavBar
import com.example.yourhealthlog.components.CustomTopAppBar
import com.example.yourhealthlog.sections.HomeKey
import com.example.yourhealthlog.sections.HomeScreen
import com.example.yourhealthlog.sections.NutritionKey
import com.example.yourhealthlog.sections.NutritionScreen
import com.example.yourhealthlog.sections.UnknownKey
import com.example.yourhealthlog.sections.navKeyToTitle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val backstack = rememberNavBackStack(HomeKey, NutritionKey)

            Scaffold(
                topBar = {
                    CustomTopAppBar(
                        navKeyToTitle(backstack.last(),
                            LocalContext.current)
                    )
                },
                bottomBar = {
                    CustomNavBar(backstack)
                },
            )
            { padding ->
                Box(modifier = Modifier
                    .padding(padding)
                )
                {
                    NavDisplay(
                        backstack,
                        onBack = { backstack.removeLastOrNull() },
                        entryProvider = { key ->
                            when (key) {
                                is HomeKey -> NavEntry(key) { HomeScreen(backstack) }
                                is NutritionKey -> NavEntry(key) { NutritionScreen(backstack) }
                                else -> NavEntry(UnknownKey) { Text(text = "unknown") }
                            }
                        }
                    )
                }
            }
        }
    }
}
        