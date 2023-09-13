package utils

import java.awt.image.BufferedImage;

fun resizeImage(img: BufferedImage, tWidth: Int, tHeight: Int) : BufferedImage {
    val resizedImg = BufferedImage(tWidth, tHeight, BufferedImage.TYPE_INT_ARGB)
    resizedImg.createGraphics().drawImage(img, 0, 0, tWidth, tHeight, null)
    return resizedImg
}