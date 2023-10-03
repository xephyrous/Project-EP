package utils

import java.awt.*
import java.awt.image.BufferedImage
import java.util.Vector

class Sprite(
    var x: Double,
    var y: Double,
    var width: Int,
    var height: Int
) : DrawObject, PhysicsObject {
    override var visible: Boolean = true
    override val uuid: Int = register()
    private lateinit var texture: BufferedImage
    private lateinit var hitbox: Vector<HitboxShape>

    fun setImage(img: BufferedImage) {
        texture = createGPUImage(img)
    }

    fun getImage() : Image { return texture }

    fun move(xSpeed: Double, ySpeed: Double, delta: Long) {
        x += ((delta * xSpeed) / 1000)
        y += ((delta * ySpeed) / 1000)
    }

    fun addHitbox(obj: HitboxShape) { hitbox.add(obj) }

    override fun draw(target: Graphics) {
        target.drawImage(texture, x.toInt(), y.toInt(), null)
        target.dispose()
    }
}