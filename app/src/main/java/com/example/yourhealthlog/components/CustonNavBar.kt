package com.example.yourhealthlog.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Kitchen
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonShapes
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.yourhealthlog.R
import com.example.yourhealthlog.sections.HomeKey
import com.example.yourhealthlog.sections.NutritionKey
import com.example.yourhealthlog.sections.UnknownKey

data class CustomNavBarItem (
    val navKey: NavKey,
    val description: String,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector
)

@Composable
fun CustomNavBar(backstack: NavBackStack<NavKey>)
{
    val activeElement = remember { mutableIntStateOf(0) }

    val items = listOf(
        CustomNavBarItem(
            navKey = HomeKey,
            description = stringResource(R.string.home_title),
            activeIcon = Icons.Filled.Home,
            inactiveIcon = Icons.Outlined.Home
        ),
        CustomNavBarItem(
            navKey = NutritionKey,
            description = stringResource(R.string.nutrition_title),
            activeIcon = Icons.Filled.Kitchen,
            inactiveIcon = Icons.Outlined.Kitchen
        )
    )

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        horizontalArrangement = Arrangement.SpaceAround
    )
    {
        items.forEachIndexed{ index, item ->
            Button(
                onClick = {
                    if (item.navKey != HomeKey)
                        backstack.add(item.navKey)
                    else
                    {
                        backstack.removeIf { it != HomeKey }
                    }
                    activeElement.intValue = index
                }
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                )
                {
                    Icon(
                        imageVector = if(activeElement.intValue == index) item.activeIcon else item.inactiveIcon,
                        contentDescription = item.description
                    )
                    Text(text = item.description)
                }
            }
        }
    }

}
