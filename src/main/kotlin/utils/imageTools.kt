package utils

import java.awt.*
import java.awt.image.BufferedImage
import java.util.Vector
import kotlin.math.ceil
import kotlin.math.floor

fun resizeImage(img: BufferedImage, tWidth: Int, tHeight: Int) : BufferedImage {
    val resizedImg = BufferedImage(tWidth, tHeight, BufferedImage.TYPE_INT_ARGB)
    resizedImg.createGraphics().drawImage(img, 0, 0, tWidth, tHeight, null)
    return resizedImg
}

fun createGPUImage(width: Int, height: Int, colSpace: Int): BufferedImage {
    val gConfig: GraphicsConfiguration =
        GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice.defaultConfiguration
    return gConfig.createCompatibleImage(width, height, Transparency.BITMASK)
}

fun createGPUImage(img: BufferedImage) : BufferedImage {
    val gConfig: GraphicsConfiguration =
        GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice.defaultConfiguration
    val gpuImage: BufferedImage = gConfig.createCompatibleImage(img.getWidth(null), img.getHeight(null), Transparency.BITMASK)
    gpuImage.graphics.drawImage(img, 0, 0, null)
    return gpuImage
}

fun createGradient(width: Int, height: Int, colors: Vector<Color>) : BufferedImage {
    val img: BufferedImage = createGPUImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val gpx = img.createGraphics()

    val offset = width / (colors.size - 1)

    for(j: Int in 0 ..< colors.size - 1) {
        for (i: Int in 0 ..< offset) {
            val rStep = (colors[j + 1].red - colors[j].red) / offset
            println(colors[j].red + rStep)
            gpx.color = Color(
                (colors[j].red + ((colors[j + 1].red - colors[j].red) / offset) * (i + 1)),
                (colors[j].green + ((colors[j + 1].green - colors[j].green) / offset) * (i + 1)),
                (colors[j].blue + ((colors[j + 1].blue - colors[j].blue) / offset) * (i + 1)),
            )
            gpx.fillRect(i + (offset * j), 0, 1, height)
        }
    }

    return img
}