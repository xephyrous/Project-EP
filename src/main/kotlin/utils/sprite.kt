package utils

import java.awt.Color
import java.awt.Graphics2D
import java.awt.GraphicsConfigTemplate
import java.awt.GraphicsConfiguration
import java.awt.GraphicsEnvironment
import java.awt.Image
import java.awt.Transparency
import java.awt.image.BufferedImage
import java.awt.image.ImageObserver

public class Sprite(x: Double, y: Double, width: Int, height: Int) : drawObject, physicsObject {
    public var x = x
    public var y = y
    var width = width
    var height = height
    override val uuid: Int = register()
    lateinit var texture: BufferedImage

    fun setImage(img: BufferedImage) {
        val gConfig: GraphicsConfiguration =
            GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice.defaultConfiguration
        val gpuImage: Image = gConfig.createCompatibleImage(img.getWidth(null), img.getHeight(null), Transparency.BITMASK)
        texture = img
    }
    fun getImage() : Image { return texture }

    fun move(xSpeed: Double, ySpeed: Double, delta: Long) {
        x += ((delta * xSpeed) / 1000)
        y += ((delta * ySpeed) / 1000)
    }

    override fun draw(wind: epWindow) {
        val gpx: Graphics2D = wind.bufStrat.drawGraphics as Graphics2D
        gpx.drawImage(wind.backImg, 0, 0, null)
        gpx.drawImage(texture, x.toInt(), y.toInt(), null)
        gpx.dispose()
        wind.bufStrat.show()
    }
}