package com.example.sixthlesson.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sixthlesson.ChildrenFunctions
import com.example.sixthlesson.DatabaseInterface
import com.example.sixthlesson.StandardWindowFunctions
import com.example.sixthlesson.adapter.ChildAdapter
import com.example.sixthlesson.databinding.FragmentChildrenBinding

class ChildFragment : Fragment() {

    private lateinit var binding: FragmentChildrenBinding
    private lateinit var adapter: ChildAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChildrenBinding.inflate(inflater, container, false)

        val databaseInterface = requireActivity() as DatabaseInterface

        adapter = ChildAdapter(object : DatabaseInterface{
            override fun updateDBData() {
                databaseInterface.updateDBData()
            }

        })

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)

        binding.backButton.setOnClickListener {
            val standardWindowFunctions = requireActivity() as StandardWindowFunctions
            standardWindowFunctions.backToMainMenu()
        }

        binding.firstBlock.setOnClickListener {
            val childrenFunctions = requireActivity() as ChildrenFunctions
            childrenFunctions.switchToParent()
        }

        binding.recycleviewchildren.layoutManager = layoutManager
        binding.recycleviewchildren.adapter = adapter

        return binding.root
    }


}