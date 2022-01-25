package com.example.thirdlesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thirdlesson.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    private lateinit var binding: FragmentThirdBinding
    private var index = 2
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        btnNextClick()
        btnPrevClick()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlus.setOnClickListener {
            setTextFields("+")
        }

        binding.btnMin.setOnClickListener {
            setTextFields("-")
        }

        binding.btnMult.setOnClickListener {
            setTextFields("*")
        }

        binding.btnDiv.setOnClickListener {
            setTextFields("/")
        }

        binding.btnDel.setOnClickListener {
            Calculator.operation = binding.operation.text.toString()
            if (Calculator.operation.isNotEmpty()) {
                binding.operation.text = Calculator.operation.substring(0, Calculator.operation.length - 1)
            }
        }

    }

    private fun setTextFields(str: String) {
        Calculator.operation = binding.operation.append(str).toString()
    }

    private fun btnNextClick(){
        binding.btnNext.setOnClickListener {
            val switchFragment = requireActivity() as SwitchFragment
            Calculator.operation = binding.operation.text.toString()
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
