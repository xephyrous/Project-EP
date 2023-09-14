package utils

import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.util.Vector
import java.awt.*

var currID: Int = 0

/*
 * Remember to call register() inside the init{...} block of a drawObject or physicsObject
 * ---> override val uuid: Int = register()
 * This sets the object with a UUID and updates the global UUID
 * If an object's UUID isn't set, the window get very confused and scared (no good)
 */

fun register() : Int {
    ++currID
    return (currID - 1)
}

interface drawObject {
    fun draw(wind: epWindow)
    val uuid: Int
}

interface physicsObject {
    val uuid: Int
}

interface shapeObject {
    val uuid: Int
    var shapeImg: BufferedImage
    var colors: Vector<Color>

    fun rotate(deg: Int)
    fun setColor(col: Color)
    fun setGradient(colors: Vector<Color>)
    fun rebuildShape()
}

interface hitboxShape {
    var points: Vector<Pair<Double, Double>>

    fun build()
    fun inShape(pX: Double, pY: Double) : Boolean
}