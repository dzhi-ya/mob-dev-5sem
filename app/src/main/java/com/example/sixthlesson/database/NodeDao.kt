package com.example.sixthlesson.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NodeDao {
    @Query("SELECT * FROM Node")
    fun getAll(): MutableList<Node>

    @Insert
    fun insertAll(vararg nodes: Node)

    @Query("DELETE FROM Node")
    fun clearTable()

}