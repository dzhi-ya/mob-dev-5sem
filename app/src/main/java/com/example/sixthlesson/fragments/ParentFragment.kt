package com.example.sixthlesson.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sixthlesson.DatabaseInterface
import com.example.sixthlesson.ParentFunctions
import com.example.sixthlesson.StandardWindowFunctions
import com.example.sixthlesson.adapter.ParentAdapter
import com.example.sixthlesson.databinding.FragmentParentBinding


class ParentFragment : Fragment() {

    private lateinit var binding: FragmentParentBinding
    private lateinit var adapter: ParentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentParentBinding.inflate(inflater, container, false)

        val databaseInterface = requireActivity() as DatabaseInterface

        adapter = ParentAdapter(object: DatabaseInterface{
            override fun updateDBData() {
                databaseInterface.updateDBData()
            }

        })

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)

        binding.backButton.setOnClickListener {
            val standardWindowFunctions = requireActivity() as StandardWindowFunctions
            standardWindowFunctions.backToMainMenu()
        }

        binding.secondBlock.setOnClickListener {
            val parentFunctions = requireActivity() as ParentFunctions
            parentFunctions.switchToChildren()
        }

        binding.recycleviewprivate.layoutManager = layoutManager
        binding.recycleviewprivate.adapter = adapter

        return binding.root
    }

}