package utils

public class RectangleShape(
    var x: Double,
    var y: Double,
    var width: Int,
    var height: Int
) : shapeObject {
    override val uuid: Int = register()
}