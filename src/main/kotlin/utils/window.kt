package utils

import com.sun.org.apache.xpath.internal.operations.Bool
import java.awt.*
import java.awt.event.*
import java.awt.image.*
import javax.swing.*
import java.util.Vector


public class epWindow(
    private var title: String,
    private var width: Int,
    private var height: Int
) : JFrame() {
    val bufStrat: BufferStrategy
    var backImg: BufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    private val windObjects: Vector<drawObject> = Vector<drawObject>()
    private val windObjectMask: Vector<Int> = Vector<Int>()

    //Initialize main window properties, double buffering, and background
    init {
        setTitle(title)
        setSize(width, height)
        isResizable = false
        isVisible = true

        createBufferStrategy(2)
        bufStrat = bufferStrategy

        backImg.graphics.color = Color.red
        backImg.graphics.fillRect(0, 0, backImg.width, backImg.height)
    }

    //Handles resize changes, updates size variables and background image
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

    public fun addObject(obj: drawObject) { windObjects.add(obj) }

    public fun removeObject(obj: drawObject) {
        for(i: Int in 0..windObjects.size) {
            if(windObjects[i].uuid == obj.uuid) { windObjects.removeAt(i) }
        }
    }

    public fun maskObject(obj: drawObject) { windObjectMask.add(obj.uuid) }

    public fun unmaskObject(obj: drawObject) {
        for(i: Int in 0..windObjectMask.size) {
            if(windObjectMask[i] == obj.uuid) { windObjectMask.removeAt(i) }
        }
    }

    public fun drawObjects() {
        val start: Long = System.nanoTime()
        for(i: Int in 0..<windObjects.size) {
            var mask: Boolean = false
            if(windObjectMask.size != 0) {
                for(n: Int in windObjectMask) {
                    if(n == windObjects[i].uuid) { mask = true; break; }
                }
            }
            if(!mask) { windObjects[i].draw(this) }
        }
    }
}