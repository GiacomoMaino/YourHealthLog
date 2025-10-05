package com.example.yourhealthlog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.yourhealthlog.components.CustomNavBar
import com.example.yourhealthlog.components.CustomTopAppBar
import com.example.yourhealthlog.sections.BiometricLoginKey
import com.example.yourhealthlog.sections.BiometricLoginScreen
import com.example.yourhealthlog.sections.HomeKey
import com.example.yourhealthlog.sections.HomeScreen
import com.example.yourhealthlog.sections.NutritionKey
import com.example.yourhealthlog.sections.NutritionScreen
import com.example.yourhealthlog.sections.UnknownKey
import com.example.yourhealthlog.sections.navKeyToTitle

class MainActivity : FragmentActivity() {
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val loggedIn = remember { mutableStateOf(false) }

            SharedTransitionLayout {
                CompositionLocalProvider{
                    val backstack = rememberNavBackStack(HomeKey, BiometricLoginKey)
                    Scaffold(
                        topBar = {
                            if (loggedIn.value)
                                CustomTopAppBar(
                                    navKeyToTitle(
                                        backstack.last(),
                                        LocalContext.current
                                    )
                                )
                        },
                        bottomBar = {
                            if (loggedIn.value)
                                CustomNavBar(backstack)
                        },
                    )
                    { padding ->
                        Box(
                            modifier = Modifier
                                .padding(padding)
                        )
                        {
                            NavDisplay(
                                backstack,
                                onBack = { backstack.removeLastOrNull() },
                                entryProvider = { key ->
                                    when (key) {
                                        is HomeKey -> NavEntry(key) { HomeScreen() }
                                        is NutritionKey -> NavEntry(key) { NutritionScreen() }
                                        is BiometricLoginKey -> NavEntry(key) { BiometricLoginScreen(onSuccess = {
                                            loggedIn.value = true
                                            backstack.removeIf { it == BiometricLoginKey }
                                        })}
                                        else -> NavEntry(UnknownKey) { Text(text = "unknown") }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
        