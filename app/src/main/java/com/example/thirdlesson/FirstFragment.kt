package com.example.thirdlesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thirdlesson.databinding.FragmentFirstBinding
import kotlin.system.exitProcess

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private var index = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        btnNextClick()
        btnPrevClick()

        return binding.root
    }

    private fun btnNextClick() {
        binding.btnNext.setOnClickListener {
            val switchFragment = requireActivity() as SwitchFragment
            try {
                Calculator.firstVal = binding.firstValue.text.toString().toDouble()
            }
            catch (e: Exception) {
                Calculator.firstVal = null
            }
            switchFragment.nextFragment(index)
        }
    }

    private fun btnPrevClick(){
        binding.btnPrev.setOnClickListener {
            exitProcess(0)
        }
    }
}