package com.example.yourhealthlog.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.yourhealthlog.R
import com.example.yourhealthlog.sections.HomeKey
import com.example.yourhealthlog.sections.NutritionKey
import com.example.yourhealthlog.sections.UnknownKey


@Composable
fun CustomNavBar(backstack: NavBackStack<NavKey>)
{
    val items = listOf(
        Pair(stringResource(R.string.home_title), HomeKey),
        Pair(stringResource(R.string.nutrition_title), NutritionKey),
    )

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        horizontalArrangement = Arrangement.Center
    )
    {
        items.forEachIndexed{ index, item ->

            Button(
                onClick = {
                    backstack.add(item.second)
                }
            ) {
                Column {
                    Text(text = item.first)
                }
            }
        }
    }

}
