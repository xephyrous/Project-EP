import utils.*
import utils.Window
import java.awt.*
import utils.Button
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main() {
    whereIsHe()

    var lastLoopTime: Long = System.currentTimeMillis()
    var running: Boolean = true

    //Create main window
    val screenSize: Dimension = Toolkit.getDefaultToolkit().screenSize
    val mainWind = Window("Project EP", screenSize.width, screenSize.height)

    //Create title page
    val titleScreen = Page(mainWind, 0, 0, screenSize.width, screenSize.height)

    //Project EP logo
    val imgF = File("src/main/Assets/project_ep_logo.png")
    val img: BufferedImage = ImageIO.read(imgF)
    val testSprite: Sprite = Sprite(0.0, 0.0, img.width, img.height)
    testSprite.setImage(img)
    titleScreen.addObject(testSprite)

    val btn = Button("Test Button", 100, 100, 50, 50)
    titleScreen.addObject(btn)

    mainWind.addPage(titleScreen)

    //val frameHandler = FrameHandler(mainWind)

    //Main loop
    while(running) {
        //frameHandler.startFrame()

        val delta: Long = System.currentTimeMillis() - lastLoopTime
        lastLoopTime = System.currentTimeMillis()

        mainWind.drawPage(titleScreen)

        //frameHandler.update()
    }
}