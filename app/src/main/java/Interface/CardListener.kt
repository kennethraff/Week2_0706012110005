package Interface
// INFO: Kalau di android studio saya saat run pertama error, harus rebuild project baru di run biar bisa jalan:)
interface CardListener {
    fun onCardClick(edit:Boolean, position: Int)
}