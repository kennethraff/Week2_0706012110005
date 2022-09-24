package Model

import model.Animal

open class Ayam (nama:String,jenis: String?, usia: String?) :Animal (nama, jenis, usia){
    override fun makesound(): String {
        return "Kokk, kokkk, kokkk"
    }

}