package utils

import java.awt.*
import java.awt.event.*
import java.awt.image.*
import javax.swing.*


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
        isResizable = false
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

    init {
        addComponentListener(object : ComponentAdapter() {
            override fun componentResized(evt: ComponentEvent) {
                val c = evt.source as Component
                width = getWidth()
                height = getHeight()
                val resizedImg: BufferedImage = resizeImage(backImg, width, height)
                backImg = resizedImg
            }
        })
    }
}