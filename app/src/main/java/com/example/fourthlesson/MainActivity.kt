package com.example.fourthlesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fourthlesson.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

interface ActivityFunctions {
    fun cardEvent(e: String)
    fun likeEvent(e: String)
}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)

        adapter = Adapter(object : ActivityFunctions{
            override fun cardEvent(e: String) {
                Snackbar.make(binding.root, "Нажата карточка: $e", 2000).show()
            }
            override fun likeEvent(e: String){
                Snackbar.make(binding.root, "Нажат лайк: $e", 2000).show()
            }
        })

        val layoutManager = LinearLayoutManager(this)
        binding.apply {
            rcView.layoutManager = layoutManager
            rcView.adapter = adapter
        }
    }
}