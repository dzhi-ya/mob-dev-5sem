package com.example.sixthlesson.database

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken

class NodesConverter {

    @TypeConverter
    fun fromNodes(nodes: List<Node>): String{
        return Gson().toJson(nodes)
    }

    @TypeConverter
    fun toNodes(nodes: String): List<Node>{
        val listType = object : TypeToken<List<Node>>() {}.type
        return Gson().fromJson(nodes, listType)
    }

}