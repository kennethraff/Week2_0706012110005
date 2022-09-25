package Model

import model.Animal
// INFO: Kalau di android studio saya saat run pertama error, harus rebuild project baru di run biar bisa jalan:)
open class Kambing(nama:String,jenis: String?, usia: String?) : Animal(nama, jenis, usia){
    override fun makesound(): String {
        return "Mbekkkkkkkkk"
    }
}