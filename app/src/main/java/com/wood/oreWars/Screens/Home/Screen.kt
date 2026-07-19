package com.wood.oreWars.Screens.Home

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wood.oreWars.composable.SimpleScaffold
import com.wood.oreWars.composable.StoneButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(nav: NavController, activity: Activity) {

    var ifShowMenu by remember { mutableStateOf(false) }

    SimpleScaffold(
        title = "矿石大陆",
        action = {
            IconButton(onClick = { onSettingClick(nav) }) { Icon(Icons.Default.Settings, contentDescription = "设置") }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            //开始游戏按钮
            Column {
                Box{
                    StoneButton({ ifShowMenu = true}, text = "开始游戏")
                    DropdownMenu(expanded = ifShowMenu, onDismissRequest = { ifShowMenu = false }) {
                        DropdownMenuItem(text = { Text("单人游戏") }, onClick = { nav.navigate("single_game"); ifShowMenu = false })
                        DropdownMenuItem(text = { Text("多人游戏") }, onClick = { nav.navigate("multi_game"); ifShowMenu = false })
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                StoneButton({ onExitClick(activity = activity) }, text = "退出游戏")
            }
        }
    }
}
