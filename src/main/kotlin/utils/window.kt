package utils

import javax.swing.*
import java.awt.*
import java.awt.image.*

import java.awt.event.*

public class epWindow(
    private var title: String,
    private var width: Int,
    private var height: Int
) : JFrame() {
    val bufStrat: BufferStrategy
    var backImg: BufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

    init {
        setTitle(title)
        setSize(width, height)
        isVisible = true

        createBufferStrategy(2)
        bufStrat = bufferStrategy
    }

    init {
        backImg.graphics.color = Color.white
        backImg.graphics.fillRect(0, 0, backImg.width, backImg.height)
    }

    public fun addObject(obj: drawObject) {

    }

    public fun removeObject(obj: drawObject) {

    }

    public fun componentResized(ce: ComponentEvent) {
        println(width)
        println(height)
    }
}