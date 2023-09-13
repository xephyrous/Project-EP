import utils.Sprite
import utils.epWindow
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main() {
    //Create main window
    val mainWind = epWindow("Project EP", 800, 400)
    mainWind.isVisible = true


    val imgF = File("src/main/Assets/motherboard.png")
    val img: BufferedImage = ImageIO.read(imgF)
    val testSprite: Sprite = Sprite(0, 0, img.width, img.height)

    testSprite.setImage(img)
    while(true)
        testSprite.draw(mainWind.getGraphics())
}