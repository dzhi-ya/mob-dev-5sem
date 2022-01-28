package com.example.sixthlesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sixthlesson.database.AppDatabase
import com.example.sixthlesson.databinding.ActivityMainBinding

import com.example.sixthlesson.database.NodeDao

import com.example.sixthlesson.fragments.ChildFragment
import com.example.sixthlesson.fragments.DialogFragment
import com.example.sixthlesson.fragments.MainFragment
import com.example.sixthlesson.fragments.ParentFragment

interface activityFunctions{
    fun disableModalState()
}
interface ParentFunctions{
    fun switchToChildren()
}
interface ChildrenFunctions{
    fun switchToParent()
}
interface StandardWindowFunctions{
    fun backToMainMenu()
}
interface SwitchFragmentsMain{
    fun openNodeTreeWindow()
}
interface UpdateMainFragmentData{
    fun updateMainAdapterData()
}
interface DatabaseInterface{
    fun updateDBData()
}
class MainActivity : AppCompatActivity(), activityFunctions, SwitchFragmentsMain, ParentFunctions, ChildrenFunctions, StandardWindowFunctions, UpdateMainFragmentData, DatabaseInterface {

    private lateinit var binding: ActivityMainBinding
    private var modalState: Boolean = false

    private lateinit var nodeDao: NodeDao

    private var mainFragment = MainFragment()
    private var dialogFragment = DialogFragment()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Загрузка главного экрана
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, mainFragment).commit()

        // Кнопка добавления
        binding.modalButton.setOnClickListener {
            if( !modalState ) supportFragmentManager.beginTransaction().replace(R.id.modal_window, dialogFragment).commit()
            modalState = true
        }

        // Connection to DB
        val db = AppDatabase.getDatabase(this)

        nodeDao = db.nodeDao()

        SimpleDataHolder.preloadDataToDB(nodeDao)

    }

    override fun disableModalState() {
        supportFragmentManager.beginTransaction().remove(dialogFragment).commit()
        this.modalState = false
    }

    override fun openNodeTreeWindow() {
        if (modalState) return
        binding.modalButton.hide()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, ParentFragment()).commit()
    }

    override fun switchToChildren() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, ChildFragment()).commit()
    }

    override fun switchToParent() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, ParentFragment()).commit()
    }

    override fun backToMainMenu() {
        binding.modalButton.show()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, mainFragment).commit()
    }

    override fun updateMainAdapterData() {
        mainFragment.requireUpdateAdapter()
    }

    override fun updateDBData() {
        SimpleDataHolder.updateDBData(nodeDao)
    }

}