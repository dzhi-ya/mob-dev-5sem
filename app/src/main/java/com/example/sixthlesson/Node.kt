package com.example.sixthlesson

data class Node(
    val index: Int,
    val value: Int,
    var nodes: List<Node>
)