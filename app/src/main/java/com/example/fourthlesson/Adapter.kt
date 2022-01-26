package com.example.fourthlesson

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fourthlesson.databinding.PersonCardBinding

class Adapter(private val activityFunctions: ActivityFunctions): RecyclerView.Adapter<Adapter.PersonViewHolder>() {
    private var personList = PersonHolder.getPersons()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PersonCardBinding.inflate(inflater, parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]

        holder.itemView.setOnClickListener{
            activityFunctions.cardEvent(person.title)
        }

        with(holder.binding) {
            like.setOnClickListener {
                activityFunctions.likeEvent(person.title)
            }
            avatar.setImageResource(person.imgId)
            tvTitle.text = person.title
            tvDate.text = person.date
            tvSex.text = person.sex
            tvDescription.text = person.description
        }
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    class PersonViewHolder(var binding: PersonCardBinding): RecyclerView.ViewHolder(binding.root)
}