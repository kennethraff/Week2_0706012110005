package Model

import model.Animal

open class Kambing(nama:String,jenis: String?, usia: String?) : Animal(nama, jenis, usia){
    override fun makesound(): String {
        return "Mbekkkkkkkkk"
    }
}