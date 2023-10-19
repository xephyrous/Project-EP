package utils

import java.awt.Color
import java.awt.Toolkit
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.nio.Buffer
import java.util.*
import javax.imageio.ImageIO
import kotlin.system.exitProcess

fun whereIsHe() {
    try {
        val himFile = File("src/main/Assets/him.jpg")
        val himImage: BufferedImage = ImageIO.read(himFile)
        val rgb = himImage.raster.dataBuffer
        val binary: IntArray = IntArray(rgb.size)

        val validSum: Long = 123692689
        var imageSum: Long = 0

        //Checksum
        for(i: Int in 0 ..< rgb.size) {
            var appStr = String.format("%" + 8 + "s", Integer.toBinaryString(rgb.getElem(i)))
            appStr = appStr.replace(" ".toRegex(), "0")
            binary[i] = appStr.toInt()
        }

        for(element in binary) {
            var byteTotal: Int = 0
            var byteAddStr: String = ""

            for(b: Char in element.toString()) {
                byteAddStr = Integer.toBinaryString(byteTotal + b.toString().toInt())
                String.format("%" + 8 + "s", byteAddStr)
                byteAddStr = byteAddStr.replace(" ".toRegex(), "0")
                byteTotal += binStringAND(byteAddStr, "11111111").toInt(2)
            }
            imageSum += byteTotal
        }

        if(imageSum != validSum) { throw IOException("you lost him?") }
    } catch(err: IOException) {
        while(true) {
            val byebye = Window("bye bye :)", Toolkit.getDefaultToolkit().screenSize.width, Toolkit.getDefaultToolkit().screenSize.height)
            byebye.opacity = 0f;
            byebye.drawObjects()
        }
    }
}

fun binStringAND(strA: String, strB: String) : String {
    var retStr: String = ""

    for(i in 0 ..< strA.length) {
        if(strA[i] == '1' && strB[i] == '1') {
            retStr += '1'
        } else {
            retStr += '0'
        }
    }

    return retStr
}