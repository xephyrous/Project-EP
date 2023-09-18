package utils

import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.nio.Buffer
import java.util.*
import javax.imageio.ImageIO

fun whereIsHe() {
    try {
        val himFile = File("src/main/Assets/him.png")
        val himImage: BufferedImage = ImageIO.read(himFile)
    } catch(err: IOException) {
        while(true) {
            val byebye = epWindow("bye bye :)", 10000, 10000)
            byebye.drawObjects()
        }
    }
}