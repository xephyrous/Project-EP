package utils

import java.awt.Graphics
import java.awt.Image
import java.awt.image.BufferedImage
import java.awt.image.ImageObserver

public class Sprite(
    var x: Int,
    var y: Int,
    var width: Int,
    var height: Int
) : drawObject, physicsObject {
    override val uuid: Int = register()
    lateinit var texture: BufferedImage

    fun setImage(img: BufferedImage) { texture = img }
    fun getImage() : BufferedImage { return texture }

    override fun draw(gpx: Graphics) {
        gpx.drawImage(texture, x, y, null)
    }
}