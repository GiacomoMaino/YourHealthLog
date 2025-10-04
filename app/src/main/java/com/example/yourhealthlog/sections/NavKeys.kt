package com.example.yourhealthlog.sections

import android.content.Context
import androidx.navigation3.runtime.NavKey
import com.example.yourhealthlog.R
import kotlinx.serialization.Serializable
import java.io.Serial

@Serializable
data object HomeKey: NavKey
@Serializable
data object NutritionKey: NavKey
@Serializable
data object UnknownKey : NavKey

fun navKeyToTitle(navKey: NavKey, context: Context): String
{
    return when(navKey)
    {
        HomeKey ->  context.resources.getString(R.string.home_title)
        NutritionKey ->  context.resources.getString(R.string.nutrition_title)
        else -> throw Error("Unknown navKey")
    }
}