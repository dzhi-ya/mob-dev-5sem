package com.example.sixthlesson.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sixthlesson.DatabaseInterface
import com.example.sixthlesson.SimpleDataHolder
import com.example.sixthlesson.UpdateMainFragmentData
import com.example.sixthlesson.activityFunctions
import com.example.sixthlesson.databinding.FragmentDialogFragmentBinding

class DialogFragment : Fragment() {

    private lateinit var binding: FragmentDialogFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDialogFragmentBinding.inflate(inflater, container, false)

        binding.exitButton.setOnClickListener {
            val activityFunctions = requireActivity() as activityFunctions
            activityFunctions.disableModalState()
        }

        binding.addButton.setOnClickListener {

            if (binding.sampleInput.text.toString().isEmpty()) return@setOnClickListener

            SimpleDataHolder.addSimpleNodeList(Integer.parseInt(binding.sampleInput.text.toString()))
            binding.sampleInput.text.clear()

            val activityFunctions = requireActivity() as activityFunctions
            activityFunctions.disableModalState()

            val updateMainFragmentData = requireActivity() as UpdateMainFragmentData
            updateMainFragmentData.updateMainAdapterData()

            val databaseInterface = requireActivity() as DatabaseInterface
            databaseInterface.updateDBData()
        }

        return binding.root

    }

}