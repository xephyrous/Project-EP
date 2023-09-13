package utils

import javax.swing.*
import java.util.Vector

import utils.Sprite

public class epWindow(
    private var title: String,
    private var width: Int,
    private var height: Int
) : JFrame() {
    init {
        setTitle(title)
        setSize(width, height)
    }

    public fun addObject(obj: drawObject) {

    }

    public fun removeObject(obj: drawObject) {

    }
}