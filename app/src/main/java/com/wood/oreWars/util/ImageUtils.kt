package com.wood.oreWars.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.annotation.DrawableRes
import java.io.ByteArrayOutputStream

fun Context.drawableToBase64(@DrawableRes id: Int, scaleDiv: Int = 1): String {
    val raw = BitmapFactory.decodeResource(resources, id)
    val bitmap = if (scaleDiv > 1) {
        Bitmap.createScaledBitmap(raw, raw.width / scaleDiv, raw.height / scaleDiv, true)
    } else {
        raw
    }
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    val bytes = stream.toByteArray()
    if (bitmap !== raw) bitmap.recycle()
    raw.recycle()
    return "data:image/png;base64," + Base64.encodeToString(bytes, Base64.NO_WRAP)
}
