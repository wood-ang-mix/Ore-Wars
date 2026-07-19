package com.wood.oreWars.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleScaffold(
    title: String,
    action: @Composable () -> Unit = {},
    content: @Composable ((PaddingValues) -> Unit)
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                actions = {
                    action()
                }
            )
        },
        content = content
    )
}