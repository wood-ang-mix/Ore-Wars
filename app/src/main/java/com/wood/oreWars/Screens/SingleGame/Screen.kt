package com.wood.oreWars.Screens.SingleGame

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(nav: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("单人游戏") }
            )
        }
    ) { innerPadding ->
        Text(
            text = "(游戏界面)",
            modifier = Modifier.padding(innerPadding)
        )
    }
}
