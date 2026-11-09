package com.example.tp8

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ex1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex1)

        val btnDownload = findViewById<Button>(R.id.btnDownload)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val txtStatus = findViewById<TextView>(R.id.txtStatus)

        btnDownload.setOnClickListener {

            btnDownload.isEnabled = false
            progressBar.progress = 0

            Thread {
                for (i in 1..10) {

                    Thread.sleep(500)

                    val pourcentage = i * 10

                    runOnUiThread {
                        progressBar.progress = pourcentage
                        txtStatus.text = "Téléchargement en cours : $pourcentage%"
                    }
                }

                runOnUiThread {
                    txtStatus.text = "Téléchargement terminé !"
                    btnDownload.isEnabled = true
                }

            }.start()
        }
    }
}