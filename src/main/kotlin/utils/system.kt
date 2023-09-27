package utils

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Container
import java.awt.Dimension
import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.image.BufferStrategy
import java.awt.image.BufferedImage
import java.util.*
import javax.swing.JFrame
import javax.swing.JPanel

class FrameHandler(
    wind: Window
) : JFrame() {
    var frameTime: Long = 0
    var prevFrameTime: Long = 0
    val bufStrat: BufferStrategy
    var frameAvg: Int = 0
    var fpsGraph: Vector<Int> = Vector<Int>()
    var maxFps: Int = 0
    var minFps: Int = 100

    init {
        dispose()
        setTitle("Frame Handler")
        setSize(400, 600)
        isResizable = false
        isVisible = true

        createBufferStrategy(2)
        bufStrat = bufferStrategy

        val gpx = bufStrat.drawGraphics
        gpx.color = Color.white
        gpx.fillRect(0, 0, 400, 600)
        bufStrat.show()
    }

    fun startFrame() {
        if(fpsGraph.size == 50) { fpsGraph.clear() }

        frameTime = System.currentTimeMillis() - prevFrameTime
        prevFrameTime = System.currentTimeMillis()
        fpsGraph.add((1000 / frameTime).toInt())

        if(frameTime > maxFps) { maxFps = frameTime.toInt() }
        if(frameTime < minFps) { minFps = frameTime.toInt() }
    }

    fun update() {
        val gpx = bufStrat.drawGraphics as Graphics2D

        gpx.color = Color.white
        gpx.fillRect(0, 0, 400, 600)
        gpx.color = Color.black
        gpx.stroke = BasicStroke(2f)

        gpx.drawRect(150, 32, 242, 78)

        gpx.drawString("Delta Time: $frameTime" + "ms", 25, 50)
        gpx.drawString("FPS: " + (1000 / frameTime), 25, 70)
        gpx.drawString("  »  Max: $maxFps", 25, 90)
        gpx.drawString("  »  Min: $minFps", 25, 110)

        for(i: Int in 1 ..< fpsGraph.size - 1) {
            gpx.drawLine(145 + (i * 5), 110 - fpsGraph[i - 1], 145 + (i * 5 + 5), 110 - fpsGraph[i])
        }

        bufStrat.show()

    }
}