import utils.*
import java.awt.*
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main() {
    whereIsHe()

    var lastLoopTime: Long = System.currentTimeMillis()
    var running: Boolean = true

    //Create main window
    val screenSize: Dimension = Toolkit.getDefaultToolkit().screenSize
    val mainWind = epWindow("Project EP", screenSize.width, screenSize.height)

    val titleScreen = epPage(mainWind, 0, 0, screenSize.width, screenSize.height)

    val imgF = File("src/main/Assets/project_ep_logo.png")
    val img: BufferedImage = ImageIO.read(imgF)
    val testSprite: Sprite = Sprite(0.0, 0.0, img.width, img.height)
    testSprite.setImage(img)
    titleScreen.addObject(testSprite)

    val testRect = RectangleShape(50.0, 50.0, 100, 100, 70.0)
    testRect.setColor(Color.blue)
    titleScreen.addObject(testRect)

    mainWind.addPage(titleScreen)

    while(running) {
        val delta: Long = System.currentTimeMillis() - lastLoopTime
        lastLoopTime = System.currentTimeMillis()

        mainWind.drawPage(titleScreen)
    }
}