package com.example.thirdlesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thirdlesson.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private var index = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        btnNextClick()
        btnPrevClick()

        return binding.root
    }

    private fun btnNextClick() {
        binding.btnNext.setOnClickListener {
            val switchFragment = requireActivity() as SwitchFragment
            try {
                Calculator.secondVal = binding.secondValue.text.toString().toDouble()
            }
            catch (e: Exception) {
                Calculator.secondVal = null
            }
            switchFragment.nextFragment(index)
        }
    }

    private fun btnPrevClick(){
        binding.btnPrev.setOnClickListener {
            val switchFragment = requireActivity() as SwitchFragment
            switchFragment.prevFragment(index)
        }
    }
}