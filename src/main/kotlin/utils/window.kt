package utils

import java.awt.*
import java.awt.image.*
import java.util.*
import javax.swing.*


class Window(
    private var title: String,
    private var width: Int,
    private var height: Int
) : JFrame() {
    val bufStrat: BufferStrategy
    var backImg: BufferedImage = createGPUImage(width, height, BufferedImage.TYPE_INT_ARGB)
    private val windObjects: Vector<DrawObject> = Vector<DrawObject>()
    private val windObjectMask: Vector<Int> = Vector<Int>()
    private val windPages: Vector<Page> = Vector<Page>()

    //Initialize main window properties, double buffering, and background
    init {
        dispose()
        setTitle(title)
        setSize(width, height)
        isResizable = false
        isUndecorated = true
        isVisible = true

        createBufferStrategy(2)
        bufStrat = bufferStrategy

        bufStrat.drawGraphics.color = Color.white
        bufStrat.drawGraphics.fillRect(0, 0, backImg.width, backImg.height)
    }

    fun setBackgroundColor(col: Color) { backImg.graphics.fillRect(0, 0, backImg.width, backImg.height) }
    fun setBackgroundImage(img: BufferedImage) { backImg = img }

    fun addObject(obj: DrawObject) { windObjects.add(obj) }

    fun removeObject(obj: DrawObject) {
        for(i: Int in 0 .. windObjects.size) {
            if(windObjects[i].uuid == obj.uuid) { windObjects.removeAt(i) }
        }
    }

    fun maskObject(obj: DrawObject) { windObjectMask.add(obj.uuid) }

    fun unmaskObject(obj: DrawObject) {
        for(i: Int in 0 .. windObjectMask.size) {
            if(windObjectMask[i] == obj.uuid) { windObjectMask.removeAt(i) }
        }
    }

    fun addPage(page: Page) { windPages.add(page) }

    fun removePage(page: Page) {
        for(i: Int in 0 .. windPages.size) {
            if(windPages[i].uuid == page.uuid) { windPages.removeAt(i) }
        }
    }

    fun drawPage(page: Page) {
        for(i: Int in 0 ..< windPages.size) {
            if(windPages[i].uuid == page.uuid) {
                windPages[i].draw(bufStrat.drawGraphics)
            }
        }
        bufStrat.show()
    }

    fun drawPages() {
        for(i: Int in 0 ..< windPages.size) {
            windPages[i].draw(this.bufStrat.drawGraphics)
        }
    }

    fun drawObjects() {
        bufStrat.drawGraphics.drawImage(backImg, 0, 0, null)
        for(i: Int in 0 ..< windObjects.size) {
            var mask = false
            if(windObjectMask.size != 0) {
                for(n: Int in windObjectMask) {
                    if(n == windObjects[i].uuid) { mask = true; break; }
                }
            }
            if(!mask) { windObjects[i].draw(bufStrat.drawGraphics) }
        }
        bufStrat.show()
    }
}

class Page(
    var x: Int,
    var y: Int,
    var width: Int,
    var height: Int
) : DrawObject {
    override var visible: Boolean = true
    override val uuid: Int = register()
    private var backImg: BufferedImage = createGPUImage(width, height, BufferedImage.TYPE_INT_ARGB)
    private val content: BufferedImage = createGPUImage(width, height, BufferedImage.TYPE_INT_ARGB)
    private val pageObjects: Vector<DrawObject> = Vector<DrawObject>()
    private val pageObjectMask: Vector<Int> = Vector<Int>()

    init {
        val gpx = backImg.graphics
        gpx.color = Color.white
        gpx.fillRect(0, 0, backImg.width, backImg.height)
    }

    fun addObject(obj: DrawObject) { pageObjects.add(obj) }

    fun removeObject(obj: DrawObject) {
        for(i: Int in 0 .. pageObjects.size) {
            if(pageObjects[i].uuid == obj.uuid) { pageObjects.removeAt(i) }
        }
    }

    fun setBackgroundColor(col: Color) { backImg.graphics.fillRect(0, 0, backImg.width, backImg.height) }

    fun setBackgroundImage(img: BufferedImage) { backImg = img }

    fun maskObject(obj: DrawObject) { pageObjectMask.add(obj.uuid) }

    fun unmaskObject(obj: DrawObject) {
        for(i: Int in 0 .. pageObjectMask.size) {
            if(pageObjectMask[i] == obj.uuid) { pageObjectMask.removeAt(i) }
        }
    }

    override fun draw(target: Graphics) {
        content.graphics.drawImage(backImg, 0, 0, null)
        for(i: Int in 0 ..< pageObjects.size) {
            var mask: Boolean = false
            if(pageObjectMask.size != 0) {
                for(n: Int in pageObjectMask) {
                    if(n == pageObjects[i].uuid) { mask = true; break; }
                }
            }
            if(!mask) { pageObjects[i].draw(content.graphics) }
        }
        target.drawImage(content, x, y, null)
    }
}