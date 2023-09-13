import utils.Sprite
import utils.epWindow
import utils.resizeImage
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import java.awt.*

fun main() {
    //Create main window - base is windowed fullscreen, add fullscreen later
    val screenSize: Dimension = Toolkit.getDefaultToolkit().screenSize
    val mainWind = epWindow("Project EP", screenSize.width, screenSize.height)

    /* Base code to load a sprite and draw to the main window
    val imgF = File("src/main/Assets/motherboard.png")
    val img: BufferedImage = ImageIO.read(imgF)
    val testSprite: Sprite = Sprite(0, 0, img.width, img.height)
    testSprite.setImage(img)

    mainWind.addObject(testSprite)
     */

    while(true) {
        //mainWind.drawObjects()
    }
}