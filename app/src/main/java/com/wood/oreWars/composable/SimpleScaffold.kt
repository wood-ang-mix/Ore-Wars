package com.wood.oreWars.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
        content = {
            WoodBox(modifier = Modifier.padding(0.dp).fillMaxSize()) {
                content(it)
            }
        }
    )
}