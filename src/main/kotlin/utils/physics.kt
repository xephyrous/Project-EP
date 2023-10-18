package utils

import java.awt.Graphics
import java.awt.image.BufferedImage
import java.util.*

public class RectHitbox(
    var x: Double, var y: Double,
    var width: Int, var height: Int
) : DrawHitbox {
    override val uuid: Int = register()
    override var visible: Boolean = true
    override lateinit var points: Vector<Pair<Double, Double>>
    private var outline: BufferedImage = createGPUImage(width, height, BufferedImage.TYPE_INT_ARGB)

    init { build() }

    override fun build() {
        points.add(Pair<Double, Double>(x, y))
        points.add(Pair<Double, Double>(x + width, y))
        points.add(Pair<Double, Double>(x + width, y + height))
        points.add(Pair<Double, Double>(x, y + height))
    }

    override fun inShape(pX: Double, pY: Double): Boolean {
        return (pX > x && pX < x + width && pY > y && pY < y + height)
    }

    override fun draw(target: Graphics) {
        val gpx: Graphics = outline.graphics
        for(i: Int in 0 ..< points.size step 2) {
            if(i == points.size - 1) {
                gpx.drawLine(
                    points[i].first.toInt(), points[i].second.toInt(),
                    points[0].first.toInt(), points[0].second.toInt()
                )
                break;
            }
            gpx.drawLine(
                points[i].first.toInt(), points[i].second.toInt(),
                points[i + 1].first.toInt(), points[i + 1].second.toInt()
            )
        }
    }
}