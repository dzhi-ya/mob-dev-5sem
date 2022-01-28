package com.example.sixthlesson.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sixthlesson.Node
import com.example.sixthlesson.R
import com.example.sixthlesson.SimpleDataHolder
import com.example.sixthlesson.databinding.MainItemBinding
import com.example.sixthlesson.fragments.SwitchFragments

class MainAdapter(private val switchFragments: SwitchFragments): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var mainItemsArray: MutableList<com.example.sixthlesson.database.Node> = SimpleDataHolder.getSimpleNodeList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MainItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = mainItemsArray[position]

        holder.itemView.setOnClickListener {
            switchFragments.openNodeTreeWindow()
            SimpleDataHolder.setCardId(position)
        }

        with(holder.binding){

            informationBlock.background = ContextCompat.getDrawable(holder.itemView.context,
                R.drawable.block_border
            )

            var check = 0
            if(item.nodes.isNotEmpty()) check += 1
            for(i in mainItemsArray){
                if(i == item) continue
                for(j in i.nodes){
                    if(j.id == item.id) {
                        check += 2
                        break
                    }
                }
                if(check >= 2) break
            }
            if (check == 1){
                informationBlock.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.rounded_yellow
                )
            }
            else if(check == 2){
                informationBlock.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.rounded_blue
                )
            }
            else if(check == 3){
                informationBlock.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.rounded_red
                )
            }

                informationBlock.text = "id: " + (position + 1) + " | value: " + item.value

        }
    }

    override fun getItemCount(): Int {
        return mainItemsArray.size
    }

    class MainViewHolder (var binding: MainItemBinding): RecyclerView.ViewHolder(binding.root)

}