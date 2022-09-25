package Database


import model.Animal
// INFO: Kalau di android studio saya saat run pertama error, harus rebuild project baru di run biar bisa jalan:)
class GlobalVar {
    companion object {
        val STORAGE_PERMISSION_CODE: Int=3
        var listDataAnimal = ArrayList<Animal>()
        val CAMERA_PERMISSION_CODE = 5
        var filterDataAnimal= ArrayList<Animal>()
//        var filterApplied = "All"
        var sess = "asdf"
    }
}