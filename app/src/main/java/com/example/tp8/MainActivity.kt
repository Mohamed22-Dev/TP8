package com.example.tp8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnEx1 = findViewById<Button>(R.id.btnEx1)
        val btnEx2 = findViewById<Button>(R.id.btnEx2)

        btnEx1.setOnClickListener {
            val intent = Intent(this, Ex1::class.java)
            startActivity(intent)
        }

        btnEx2.setOnClickListener {
            val intent = Intent(this, Ex2::class.java)
            startActivity(intent)
        }
    }
}