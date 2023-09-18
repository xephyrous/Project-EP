import utils.RectangleShape
import utils.Sprite
import utils.epWindow
import java.awt.*
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main() {
    var lastLoopTime: Long = System.currentTimeMillis()
    var running: Boolean = true

    //Create main window
    val screenSize: Dimension = Toolkit.getDefaultToolkit().screenSize
    val mainWind = epWindow("Project EP", screenSize.width, screenSize.height)

    // Base code to load a sprite and draw to the main window
    /*
    val imgF = File("src/main/Assets/project_ep_logo.png")
    val img: BufferedImage = ImageIO.read(imgF)
    val testSprite: Sprite = Sprite(0.0, 0.0, img.width, img.height)
    testSprite.setImage(img)
    mainWind.addObject(testSprite)
     */

    while(running) {
        val delta: Long = System.currentTimeMillis() - lastLoopTime
        lastLoopTime = System.currentTimeMillis()

        mainWind.drawObjects()
    }
}