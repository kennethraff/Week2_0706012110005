package com.example.week2_0706012110005

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.week2_0706012110005.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // INFO: Kalau di android studio saya saat run pertama error, harus rebuild project baru di run biar bisa jalan:)


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this, DataActivity::class.java)
            startActivity(intent)
            finish()
        },100)
    }
}