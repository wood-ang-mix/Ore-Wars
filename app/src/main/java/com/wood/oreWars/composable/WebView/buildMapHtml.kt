package com.wood.oreWars.composable.WebView

import android.nfc.tech.MifareClassic.BLOCK_SIZE
import com.wood.oreWars.backend.Block
import com.wood.oreWars.backend.GameMap


fun buildMapHtml(
    stoneBase64: String,
    redstoneBase64: String,
    woodBase64: String,
    map: GameMap
) ="""
    <!DOCTYPE html>
    <html>
        <head>
            <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
            <style>
                * { margin: 0; padding: 0; }
                body { overflow: auto; background: transparent; }
                canvas { display: block; }
            </style>
        </head>
        <body>
            <canvas id="canvas"></canvas>
            <script>
            const BLOCK_SIZE = $BLOCK_SIZE;
            const map = ${map.size};
            const images = {
                stone: "$stoneBase64",
                redstone: "$redstoneBase64",
                wood: "$woodBase64"
            };
            </script> 
        </body>
    </html>
""".trimIndent()