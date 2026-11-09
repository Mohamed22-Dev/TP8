package com.example.tp8

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ex2 : AppCompatActivity() {

    private lateinit var btnStart: Button
    private lateinit var txtStock: TextView
    private lateinit var progressBar: ProgressBar

    private var stock = 0
    private val maxStock = 10

    @Volatile
    private var running = false

    private val lock = java.lang.Object()

    private var producerThread: Thread? = null
    private var consumerThread: Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex2)

        btnStart = findViewById(R.id.btnStart)
        txtStock = findViewById(R.id.txtStock)
        progressBar = findViewById(R.id.progressBar)

        btnStart.setOnClickListener {
            startSimulation()
        }
    }

    private fun startSimulation() {

        if (running) return

        stock = 0
        running = true

        btnStart.isEnabled = false
        progressBar.visibility = View.VISIBLE

        txtStock.text = "Stock : 0 / 10"

        producerThread = Thread {
            producer()
        }

        consumerThread = Thread {
            consumer()
        }

        producerThread?.start()
        consumerThread?.start()

        Handler(Looper.getMainLooper()).postDelayed({
            stopSimulation()
        }, 20000)
    }

    private fun producer() {

        try {
            while (running) {

                var currentStock: Int? = null

                synchronized(lock) {

                    while (running && stock >= maxStock) {
                        lock.wait()
                    }

                    if (running) {
                        stock++
                        currentStock = stock
                        lock.notifyAll()
                    }
                }

                currentStock?.let {
                    runOnUiThread {
                        txtStock.text = "Stock : $it / 10"
                    }
                }

                if (running) {
                    Thread.sleep(500)
                }
            }

        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }

    private fun consumer() {

        try {
            while (running) {

                var currentStock: Int? = null

                synchronized(lock) {

                    while (running && stock <= 0) {
                        lock.wait()
                    }

                    if (running) {
                        stock--
                        currentStock = stock
                        lock.notifyAll()
                    }
                }

                currentStock?.let {
                    runOnUiThread {
                        txtStock.text = "Stock : $it / 10"
                    }
                }

                if (running) {
                    Thread.sleep(300)
                }
            }

        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }

    private fun stopSimulation() {

        if (!running) return

        running = false

        synchronized(lock) {
            lock.notifyAll()
        }

        producerThread?.interrupt()
        consumerThread?.interrupt()

        Thread {
            producerThread?.join()
            consumerThread?.join()

            runOnUiThread {
                progressBar.visibility = View.GONE
                txtStock.text = "Simulation terminée"
                btnStart.isEnabled = true
            }
        }.start()
    }
}