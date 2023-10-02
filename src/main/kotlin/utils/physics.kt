package utils

import java.util.*

public class rectHitbox(var x: Double, var y: Double, var width: Int, var height: Int) : physicsObject, hitboxShape {
    override val uuid: Int = register()
    override lateinit var points: Vector<Pair<Double, Double>>

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
}