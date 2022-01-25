package com.example.thirdlesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.thirdlesson.databinding.ActivityMainBinding

interface SwitchFragment {
    fun nextFragment(index: Int)
    fun prevFragment(index: Int)
}

class MainActivity : AppCompatActivity(), SwitchFragment {

    private lateinit var binding: ActivityMainBinding
    private var fragmentList: List<Fragment> = listOf(FirstFragment(), SecondFragment(), ThirdFragment(), FourthFragment())
    private var btnList: MutableList<Button> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        btnList.add(0, binding.btn1)
        btnList.add(1, binding.btn2)
        btnList.add(2, binding.btn3)
        btnList.add(3, binding.btn4)

        setOnClickHeaderBtn()


    }

    private fun setOnClickHeaderBtn() {
        if (fragmentList.size != btnList.size) return
        for (i in fragmentList.indices) {
            if (i != 0) btnList[i].isEnabled = false
            btnList[i].setOnClickListener {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentList[i]).commit()
            }
        }
    }
    override fun nextFragment(index: Int) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentList[index+1]).commit()
        effectBtn(index+1)
    }
    override fun prevFragment(index: Int) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentList[index-1]).commit()
    }
    private fun effectBtn(index: Int) {
        for (i in 0..index) {
            btnList[i].isEnabled = true
        }
    }
}