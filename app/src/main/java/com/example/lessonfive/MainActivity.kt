package com.example.lessonfive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lessonfive.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

interface ActivityFunctions {
    fun cardEvent(e: String)
    fun likeEvent(e: String)
}

interface TaskCallbacks{
    fun getPerson(person: Person)
}

class MainActivity : AppCompatActivity(), TaskCallbacks {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    private var fragment: AsyncTask? = null

    override fun getPerson(person: Person) {
        Log.d("my_tag", "Get")
        adapter.addPerson(person)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)

        adapter =Adapter(object : ActivityFunctions{
            override fun cardEvent(e: String) {
                Snackbar.make(binding.root, "Нажата карточка: $e", 1000).show()
            }
            override fun likeEvent(e: String){
                Snackbar.make(binding.root, "Нажат лайк: $e", 1000).show()
            }
        })

        val fragmentManager = supportFragmentManager
        val oldFragment = fragmentManager.findFragmentByTag(AsyncTask.MyTag)
        if (oldFragment == null){
            fragment = AsyncTask()
            fragmentManager.beginTransaction().add(fragment!!, AsyncTask.MyTag).commit()
        }
        else{
            fragment = oldFragment as AsyncTask
            adapter.getPreviousPersons(fragment!!.person)
        }
        val layoutManager = LinearLayoutManager(this)
        binding.apply {
            rcView.layoutManager = layoutManager
            rcView.adapter = adapter
        }
    }
}