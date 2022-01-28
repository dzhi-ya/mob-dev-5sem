package com.example.sixthlesson.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sixthlesson.SwitchFragmentsMain
import com.example.sixthlesson.adapter.MainAdapter
import com.example.sixthlesson.databinding.FragmentMainScreenBinding

interface SwitchFragments{
    fun openNodeTreeWindow()
}
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)

        val switchFragmentsMain = requireActivity() as SwitchFragmentsMain

        adapter = MainAdapter(object : SwitchFragments {
            override fun openNodeTreeWindow() {
                switchFragmentsMain.openNodeTreeWindow()
            }
        })

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)

        binding.recycleviewmainscreen.layoutManager = layoutManager
        binding.recycleviewmainscreen.adapter = adapter

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    fun requireUpdateAdapter(){
        adapter.notifyDataSetChanged()
    }

}