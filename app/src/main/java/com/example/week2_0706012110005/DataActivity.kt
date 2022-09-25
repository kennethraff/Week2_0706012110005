package com.example.week2_0706012110005

import Adapter.ListAnimalRvAdapter
import Database.GlobalVar
import Interface.CardListener
import Model.Ayam
import Model.Kambing
import Model.Sapi
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week2_0706012110005.databinding.ActivityDataBinding
import com.example.week2_0706012110005.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class DataActivity : AppCompatActivity(), CardListener{

// INFO: Kalau di android studio saya saat run pertama error, harus rebuild project baru di run biar bisa jalan:)


    private var adapter= ListAnimalRvAdapter(GlobalVar.listDataAnimal, this)
    private val adapter2 = ListAnimalRvAdapter(GlobalVar.filterDataAnimal, this)


    private lateinit var binding: ActivityDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        listener()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        textHidden()

    }


//    private fun sortAyam(){
//        for(animal in GlobalVar.listDataAnimal ) {
//            if(animal is Kambing) { GlobalVar.filterDataAnimal .add(animal) } }
//    }
//    private fun sortSapi(){
//
//    }
//    private fun sortKambing(){
//
//    }

    private fun listener() {
        binding.addHewanFABDataActivity.setOnClickListener() {
            val myIntent = Intent(this, InputActivity::class.java)
            startActivity(myIntent);


        }

        binding.filterbuttonAyamDataActivity.setOnClickListener {
            GlobalVar.sess = "ayam"
            GlobalVar.filterDataAnimal .clear()
            for(i in 0..GlobalVar.listDataAnimal.size-1){
                if(GlobalVar.listDataAnimal [i] is Ayam){
                    GlobalVar.filterDataAnimal.add(GlobalVar.listDataAnimal[i])
                }
            }

            binding.listDataRV.adapter = adapter2
            adapter.notifyDataSetChanged()
        }

        binding.filterbuttonKambingDataActivity.setOnClickListener {
            GlobalVar.sess = "kambing"
            GlobalVar.filterDataAnimal.clear()
            for(i in 0..GlobalVar.listDataAnimal.size-1){
                if(GlobalVar.listDataAnimal[i] is Kambing){
                    GlobalVar.filterDataAnimal.add(GlobalVar.listDataAnimal[i])
                }
            }
            binding.listDataRV.adapter = adapter2
            adapter.notifyDataSetChanged()
        }

        binding.filterbuttonSapiDataActivity.setOnClickListener {
            GlobalVar.sess = "sapi"
            GlobalVar.filterDataAnimal.clear()
            for(i in 0..GlobalVar.listDataAnimal.size-1){
                if(GlobalVar.listDataAnimal[i] is Sapi){
                    GlobalVar.filterDataAnimal.add(GlobalVar.listDataAnimal[i])
                }
            }
            binding.listDataRV.adapter = adapter2
            adapter.notifyDataSetChanged()
        }

        binding.filterbuttonAllDataActivity.setOnClickListener {
            GlobalVar.sess = "asdfas"
            binding.listDataRV.adapter = adapter
            adapter.notifyDataSetChanged()
        }


    }

    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(baseContext)
        binding.listDataRV.layoutManager= layoutManager //Set layout
        binding.listDataRV.adapter=adapter //Set adapter
    }

    private fun textHidden(){
        if(GlobalVar.listDataAnimal.isEmpty()){
            binding.addHewanDataActivity.alpha = 1f
        } else {
            binding.addHewanDataActivity.alpha = 0f        }
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), GlobalVar.STORAGE_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), GlobalVar.CAMERA_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Camera Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == GlobalVar.CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == GlobalVar.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCardClick(edit: Boolean, position: Int) {
        //kode untuk edit/delete
        if(edit == true){

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete data hewan")
            builder.setMessage("Yakin delete data hewan ini?")

            builder.setPositiveButton(android.R.string.yes) { function, which ->
                val snackbar = Snackbar.make(
                    binding.listDataRV,
                    "Hewan berhasil dihapus",
                    Snackbar.LENGTH_INDEFINITE
                )
                snackbar.setAction("Done") { snackbar.dismiss() }
                snackbar.setActionTextColor(Color.BLUE)
                snackbar.setBackgroundTint(Color.GRAY)
                snackbar.show()


                GlobalVar.listDataAnimal.removeAt(position)
                adapter.notifyDataSetChanged()
            }
            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(applicationContext, android.R.string.no, Toast.LENGTH_SHORT).show()
            }
            builder.show()


        }else{
            val intent = Intent(this, InputActivity::class.java).putExtra("position",position)
            startActivity(intent)
        }

    }
}