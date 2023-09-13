import utils.Sprite
import utils.epWindow
import utils.resizeImage
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main() {
    //Create main window
    val mainWind = epWindow("Project EP", 800, 400)

    /* Base code to load a sprite
    val imgF = File("src/main/Assets/motherboard.png")
    val img: BufferedImage = ImageIO.read(imgF)
    val testSprite: Sprite = Sprite(0, 0, img.width, img.height)
    testSprite.setImage(img)
     */

    while(true) {
        /* Base code to draw  a sprite
        testSprite.draw(mainWind)
         */
    }
}