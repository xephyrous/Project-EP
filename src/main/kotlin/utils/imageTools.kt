package utils

import java.awt.*
import java.awt.image.BufferedImage
import java.nio.Buffer
import java.util.Vector

fun resizeImage(img: BufferedImage, tWidth: Int, tHeight: Int) : BufferedImage {
    val resizedImg = BufferedImage(tWidth, tHeight, BufferedImage.TYPE_INT_ARGB)
    resizedImg.createGraphics().drawImage(img, 0, 0, tWidth, tHeight, null)
    return resizedImg
}

fun createGPUImage(width: Int, height: Int, colSpace: Int) : BufferedImage {
    val gConfig: GraphicsConfiguration =
        GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice.defaultConfiguration
    val gpuImage: BufferedImage = gConfig.createCompatibleImage(width, height, Transparency.BITMASK)
    return gpuImage
}

fun createGPUImage(img: BufferedImage) : BufferedImage {
    val gConfig: GraphicsConfiguration =
        GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice.defaultConfiguration
    val gpuImage: BufferedImage = gConfig.createCompatibleImage(img.getWidth(null), img.getHeight(null), Transparency.BITMASK)
    gpuImage.graphics.drawImage(img, 0, 0, null)
    return gpuImage
}

fun createGradient(width: Int, height: Int, colors: Vector<Color>) : BufferedImage {
    var gpx: BufferedImage = createGPUImage(width, height, BufferedImage.TYPE_INT_ARGB)

    return gpx
}