package com.example.sixthlesson.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Node(
    @PrimaryKey(autoGenerate = false) @NonNull val id: Int,
    @ColumnInfo(name = "value") val value: Int,
    @ColumnInfo(name = "nodes") var nodes: List<Node>
)