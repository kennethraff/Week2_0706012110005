package Model

import model.Animal

open class Sapi(nama:String,jenis: String?, usia: String?) :Animal(nama, jenis, usia){
    override fun makesound(): String {
        return "moooooooooooo"
    }
}