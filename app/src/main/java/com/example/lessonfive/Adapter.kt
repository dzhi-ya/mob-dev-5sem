package com.example.lessonfive

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lessonfive.databinding.PersonCardBinding

class Adapter(private val activityFunctions: ActivityFunctions): RecyclerView.Adapter<Adapter.PersonViewHolder>() {
    private var personList = mutableListOf<Person>()

    @SuppressLint("NotifyDataSetChanged")
    fun addPerson(person: Person)
    {
        personList.add(person)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getPreviousPersons(prevPersons: MutableList<Person>)
    {
        for (person in prevPersons)
            personList.add(person)
        notifyDataSetChanged()
    }

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