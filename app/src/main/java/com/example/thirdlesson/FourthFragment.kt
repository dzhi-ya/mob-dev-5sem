package com.example.thirdlesson

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thirdlesson.databinding.FragmentFourthBinding

class FourthFragment : Fragment() {
    private lateinit var binding: FragmentFourthBinding
    private var index = 3
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFourthBinding.inflate(inflater, container, false)
        binding.result.text = Calculator.firstVal.toString() +
                " " + Calculator.operation +
                " " + Calculator.secondVal.toString() +
                " = " + Calculator.result().toString()
        btnPrevClick()

        return binding.root
    }

    private fun btnPrevClick(){
        binding.btnPrev.setOnClickListener {
            val switchFragment = requireActivity() as SwitchFragment
            switchFragment.prevFragment(index)
        }
    }
}