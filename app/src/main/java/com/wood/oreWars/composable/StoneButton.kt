package com.wood.oreWars.composable

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Shader.TileMode
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.wood.oreWars.R


@SuppressLint("LocalContextResourcesRead")
@Composable
fun StoneButton(
    onClick: () -> Unit,
    text: String = "",
    modifier: Modifier = Modifier,
    contentColor: Color = Color(0xFFFFFFFF),
    content: @Composable () -> Unit = {},
) {
    val context = LocalContext.current
    val brush = remember {
        val raw = BitmapFactory.decodeResource(
            context.resources, R.drawable.stone
        )
        val bitmap = Bitmap.createScaledBitmap(
            raw, raw.width / 6, raw.height / 6, true
        )
        val shader = BitmapShader(bitmap, TileMode.REPEAT, TileMode.REPEAT)
        ShaderBrush(shader)
    }
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 4.dp,
            hoveredElevation = 6.dp,
            focusedElevation = 6.dp,
        ),
    ) {
        Box(
            modifier = Modifier
                .background(brush = brush)
                .padding(horizontal = 24.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            CompositionLocalProvider(LocalContentColor provides contentColor) {
                if (text.isBlank()) {
                    content()
                } else {
                    Text(text = text)
                }
            }
        }
    }
}
