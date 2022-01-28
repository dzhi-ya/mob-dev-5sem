package com.example.sixthlesson.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sixthlesson.*
import com.example.sixthlesson.databinding.MainItemBinding

class ChildAdapter(private val databaseInterface: DatabaseInterface): RecyclerView.Adapter<ChildAdapter.ChildrenViewHolder>() {

    private var mainItemsArray: MutableList<com.example.sixthlesson.database.Node> =
        SimpleDataHolder.getSimpleNodeListWithoutParentItemId(SimpleDataHolder.getCardId())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MainItemBinding.inflate(inflater, parent, false)
        return ChildrenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildrenViewHolder, position: Int) {
        val item = mainItemsArray[position]

        holder.itemView.setOnClickListener {
            SimpleDataHolder.pushEmptyNodeList(SimpleDataHolder.getCardId(), item.id)
            databaseInterface.updateDBData()
            notifyDataSetChanged()
        }

        with(holder.binding) {

            informationBlock.background = ContextCompat.getDrawable(holder.itemView.context,
                R.drawable.block_border
            )

            for(i in SimpleDataHolder.getSimpleNodeListById(SimpleDataHolder.getCardId()).nodes){
                if(i.id == item.id){
                    informationBlock.background = ContextCompat.getDrawable(holder.itemView.context,
                        R.drawable.rounded_color
                    )
                }
            }

            informationBlock.text =
                "id: " + (SimpleDataHolder.getCardId() + 1) + " | value: " + SimpleDataHolder.getSimpleNodeListById(
                    SimpleDataHolder.getCardId()
                ).value + " --- " + "id: " + (position + 1) + " | value: " + item.value
        }

    }

    override fun getItemCount(): Int {
        return mainItemsArray.size
    }

    class ChildrenViewHolder (var binding: MainItemBinding): RecyclerView.ViewHolder(binding.root)

}