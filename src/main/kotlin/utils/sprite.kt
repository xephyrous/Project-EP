package utils

import java.awt.*
import java.awt.image.BufferedImage
import java.awt.image.ImageObserver

class Sprite(x: Double, y: Double, width: Int, height: Int) : drawObject, physicsObject {
    var x = x
    var y = y
    var width = width
    var height = height
    override val uuid: Int = register()
    lateinit var texture: BufferedImage

    fun setImage(img: BufferedImage) {
        texture = createGPUImage(img)
    }
    fun getImage() : Image { return texture }

    fun move(xSpeed: Double, ySpeed: Double, delta: Long) {
        x += ((delta * xSpeed) / 1000)
        y += ((delta * ySpeed) / 1000)
    }

    override fun draw(target: Graphics) {
        target.drawImage(texture, x.toInt(), y.toInt(), null)
        target.dispose()
    }
}