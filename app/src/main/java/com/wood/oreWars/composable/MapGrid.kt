package com.wood.oreWars.composable

import android.annotation.SuppressLint
import android.nfc.tech.MifareClassic.BLOCK_SIZE
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.wood.oreWars.R
import com.wood.oreWars.backend.GameMap
import com.wood.oreWars.util.drawableToBase64


@SuppressLint("LocalContextResourcesRead", "SetJavaScriptEnabled")
@Composable
fun MapGrid(
    gameMap: GameMap,
    modifier: Modifier = Modifier,
    onClick: (Int, Int) -> Unit = gameMap.onClick
) {
    val context = LocalContext.current
    val density = context.resources.displayMetrics.density
    val blockPx = (BLOCK_SIZE * density).toInt()
    val clickCallback = rememberUpdatedState(onClick)

    val html = remember(gameMap.size, blockPx) {
        val oreImages = mapOf(
            "stone"    to context.drawableToBase64(R.drawable.stone),
            "redstone" to context.drawableToBase64(R.drawable.red_stone),
            "lapis"    to context.drawableToBase64(R.drawable.lapis),
            "coal"     to context.drawableToBase64(R.drawable.coal),
            "gold"     to context.drawableToBase64(R.drawable.gold),
            "copper"   to context.drawableToBase64(R.drawable.copper),
            "diamond"  to context.drawableToBase64(R.drawable.diamond),
        )
        val woodBase64 = context.drawableToBase64(R.drawable.wood, scaleDiv = 6)
        buildHtml(
            oreImages = oreImages,
            woodBase64 = woodBase64,
            size = gameMap.size,
            blockSize = blockPx
        )
    }

    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            WebView(ctx).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = false
                setBackgroundColor(0x00000000)
                overScrollMode = WebView.OVER_SCROLL_NEVER

                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        val json = gameMap.toJson()
                        view?.evaluateJavascript("renderMap('$json')", null)
                    }
                }

                addJavascriptInterface(object {
                    @JavascriptInterface
                    fun onBlockClick(x: Int, y: Int) {
                        post { clickCallback.value(x, y) }
                    }
                }, "Android")

                loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
            }
        }
    )
}

private fun buildHtml(
    oreImages: Map<String, String>,
    woodBase64: String,
    size: Int,
    blockSize: Int
) = """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
            <style>
                * { margin: 0; padding: 0; }
                body {
                    overflow: auto;
                    background-image: url($woodBase64);
                    background-repeat: repeat;
                }
                canvas { display: block; }
            </style>
        </head>
        <body>
            <canvas id="map"></canvas>
            <script>
                const BLOCK_SIZE = $blockSize;
                const SIZE = $size;
                const IMAGES = {
${oreImages.entries.joinToString(",\n") { """                    ${it.key}: "${it.value}"""" }}
                };
                let mapData = [];
                const loadedImages = {};

                function preloadImages(callback) {
                    const keys = Object.keys(IMAGES);
                    let count = 0;
                    keys.forEach(key => {
                        const img = new Image();
                        img.onload = () => {
                            loadedImages[key] = img;
                            count++;
                            if (count === keys.length) callback();
                        };
                        img.src = IMAGES[key];
                    });
                }

                function renderMap(json) {
                    mapData = JSON.parse(json);
                    const canvas = document.getElementById('map');
                    canvas.width = SIZE * BLOCK_SIZE;
                    canvas.height = SIZE * BLOCK_SIZE;
                    const ctx = canvas.getContext('2d');
                    preloadImages(() => {
                        mapData.forEach(block => {
                            const img = loadedImages[block.ore] || loadedImages['stone'];
                            ctx.drawImage(img, block.x * BLOCK_SIZE, block.y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                        });
                    });
                }

                document.getElementById('map').addEventListener('click', e => {
                    const rect = e.target.getBoundingClientRect();
                    const x = Math.floor((e.clientX - rect.left) / BLOCK_SIZE);
                    const y = Math.floor((e.clientY - rect.top) / BLOCK_SIZE);
                    if (x >= 0 && x < SIZE && y >= 0 && y < SIZE) {
                        Android.onBlockClick(x, y);
                    }
                });

            </script>
        </body>
        </html>
    """.trimIndent()
