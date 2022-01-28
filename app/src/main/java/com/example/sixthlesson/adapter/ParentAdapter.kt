package com.example.sixthlesson.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sixthlesson.DatabaseInterface
import com.example.sixthlesson.Node
import com.example.sixthlesson.R
import com.example.sixthlesson.SimpleDataHolder
import com.example.sixthlesson.databinding.MainItemBinding

class ParentAdapter(private val databaseInterface: DatabaseInterface) : RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {

    private var mainItemsArray: MutableList<com.example.sixthlesson.database.Node> =
        SimpleDataHolder.getSimpleNodeListWithoutChildrenItemId(SimpleDataHolder.getCardId())

    fun buggedUpdateArrayData(){
        val tempNodes = mutableListOf<com.example.sixthlesson.database.Node>()
        val mainNodes = SimpleDataHolder.getSimpleNodeList()
        for (i in mainNodes.indices) {

            var iter = 0
            for (j in mainNodes[SimpleDataHolder.getCardId()].nodes.indices) {
                if (mainNodes[SimpleDataHolder.getCardId()].nodes[j].id == mainNodes[i].id) {
                    iter = 1
                    break
                }
            }
            if(iter == 0 && SimpleDataHolder.getCardId() != i) {
                tempNodes.add(mainNodes[i])
            }
        }

        mainItemsArray = tempNodes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MainItemBinding.inflate(inflater, parent, false)
        return ParentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {

        val item = mainItemsArray[position]

        holder.itemView.setOnClickListener {
            SimpleDataHolder.pushEmptyNodeList(item.id, SimpleDataHolder.getCardId())
            databaseInterface.updateDBData()
            buggedUpdateArrayData()
            notifyDataSetChanged()
        }

        with(holder.binding) {

            informationBlock.background = ContextCompat.getDrawable(holder.itemView.context,
                R.drawable.block_border
            )

            for(i in item.nodes){
                if(i.id == SimpleDataHolder.getSimpleNodeListById(SimpleDataHolder.getCardId()).id){
                    informationBlock.background = ContextCompat.getDrawable(holder.itemView.context,
                        R.drawable.rounded_color
                    )
                }
            }

            informationBlock.text =
                "id: " + (position + 1) + " | value: " + item.value + " --- " + "id: " + (SimpleDataHolder.getCardId() + 1) + " | value: " + SimpleDataHolder.getSimpleNodeListById(
                    SimpleDataHolder.getCardId()
                ).value
        }

    }

    override fun getItemCount(): Int {
        return mainItemsArray.size
    }

    class ParentViewHolder(var binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root)

}