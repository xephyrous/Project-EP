package utils

import java.io.File

enum class Flag(val value: Int) {
    HITBOX(0)
}

class Parser(
    var flag: Int
) {
    private lateinit var parseFile: File
    fun loadFile(path: String) : Boolean {
        parseFile = File(path)
        if(!parseFile.exists()) { return false; }
        return true;
    }

    fun setParseType(newFlag: Int) { flag = newFlag }

    /*fun parse() : {
        when(flag) {
            Flag.HITBOX.value -> {
                
            }
        }
    }*/
}