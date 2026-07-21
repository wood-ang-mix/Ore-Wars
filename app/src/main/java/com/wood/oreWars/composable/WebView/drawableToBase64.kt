package com.wood.oreWars.composable.WebView

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.annotation.DrawableRes
import java.io.ByteArrayOutputStream

fun Context.drawToBase64(@DrawableRes id: Int): String {
    val bitmap = BitmapFactory.decodeResource(resources, id)
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return "data:image/png;base64," + Base64.encodeToString(stream.toByteArray(), Base64.DEFAULT)
}
