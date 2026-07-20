package com.wood.oreWars.composable

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Shader.TileMode
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.wood.oreWars.R

@SuppressLint("LocalContextResourcesRead")
@Composable
fun WoodBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    val context = LocalContext.current
    val brush = remember {
        val raw = BitmapFactory.decodeResource(
            context.resources, R.drawable.wood
        )
        val bitmap = Bitmap.createScaledBitmap(
            raw, raw.width / 6, raw.height / 6, true
        )
        val shader = BitmapShader(bitmap, TileMode.REPEAT, TileMode.REPEAT)
        ShaderBrush(shader)
    }
    Box(
        modifier = modifier
            .background(brush = brush),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            content()
        }
    }

}