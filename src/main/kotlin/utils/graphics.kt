package utils

import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.nio.Buffer
import java.util.*
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin

public class RectangleShape(
    var x: Double,
    var y: Double,
    var width: Int,
    var height: Int,
    var rotation: Double
) : drawObject, shapeObject {
    override val uuid: Int = register()
    override var visible: Boolean = true
    override lateinit var shapeImg: BufferedImage
    override var colors: Vector<Color> = Vector<Color>()

    init {
        colors.add(Color.black)
        rebuildShape()
    }

    override fun draw(target: Graphics) {
        target.drawImage(shapeImg, x.toInt(), y.toInt(), null)
        target.dispose()
    }

    override fun rotate(deg: Double) { rotation = deg }

    override fun setColor(col: Color) { colors[0] = col; rebuildShape() }

    override fun setGradient(colArr: Vector<Color>) { colors = colArr }

    override fun rebuildShape() {
        val sin: Double = abs(sin(Math.toRadians(rotation)))
        val cos: Double = abs(cos(Math.toRadians(rotation)))
        val newWidth: Int = floor(width * cos + height * sin).toInt()
        val newHeight: Int = floor(height * cos + width * sin).toInt()
        shapeImg = createGPUImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB)

        val baseImg: BufferedImage = createGPUImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB)
        val gpx: Graphics2D = baseImg.createGraphics()

        gpx.translate((newWidth - width) / 2, (newHeight - height) / 2)
        gpx.rotate(Math.toRadians(rotation), (width / 2).toDouble(), (height / 2).toDouble());

        if(colors.size == 1) {
            gpx.color = colors[0]
            gpx.fillRect(0, 0, width, height)
        } else {
            val gradient: BufferedImage = createGPUImage(
                createGradient(width, height, colors)
            )
            gpx.drawImage(gradient, 0, 0, null)
        }

        shapeImg.graphics.drawImage(baseImg, 0, 0, null)
    }
}