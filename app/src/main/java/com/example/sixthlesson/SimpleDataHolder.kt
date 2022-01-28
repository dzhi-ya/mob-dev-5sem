package com.example.sixthlesson

import androidx.core.content.ContextCompat
import com.example.sixthlesson.database.AppDatabase
import com.example.sixthlesson.database.NodeDao

object SimpleDataHolder {

    private var nodes = mutableListOf<com.example.sixthlesson.database.Node>()

    private var id: Int = 0

    private var parentId: Int = 0
    private var childId: Int = 0

    fun updateDBData(connection: NodeDao){

        connection.clearTable()

        connection.insertAll(*nodes.toTypedArray())

        val nodesFromDB: MutableList<com.example.sixthlesson.database.Node> = connection.getAll()

        nodes = nodesFromDB

    }

    fun preloadDataToDB(connection: NodeDao){

        connection.clearTable()

        connection.insertAll(
            com.example.sixthlesson.database.Node(0, 1, emptyList()),
            com.example.sixthlesson.database.Node(1, 2, emptyList()),
            com.example.sixthlesson.database.Node(2, 3, emptyList()),
            com.example.sixthlesson.database.Node(3, 4, emptyList()),
            com.example.sixthlesson.database.Node(4, 5, emptyList()),
            )

        val nodesFromDB: MutableList<com.example.sixthlesson.database.Node> = connection.getAll()

        nodes = nodesFromDB

    }

    fun pushEmptyNodeList(parentId: Int, childId: Int) {
        var containState = 0
        for(i in nodes[parentId].nodes){
            if(i.id == nodes[childId].id){
                containState = 1
                break
            }
        }
        if (containState == 1) {
            val nodes_temp = nodes[parentId].nodes.toMutableList()
            val index = nodes_temp.indexOfFirst { it.id == childId }
            nodes_temp.removeAt(index)
            var temp_list = emptyList<com.example.sixthlesson.database.Node>()
            for (i in nodes_temp.indices) {
                temp_list += listOf(
                    com.example.sixthlesson.database.Node(
                        nodes_temp[i].id,
                        nodes_temp[i].value,
                        nodes_temp[i].nodes
                    )
                )
            }

            nodes[parentId].nodes = temp_list

        }else{
            nodes[parentId].nodes += listOf(
                com.example.sixthlesson.database.Node(
                    nodes[childId].id,
                    nodes[childId].value,
                    nodes[childId].nodes
                )
            )
        }
    }

    fun addSimpleNodeList(value: Int) {
        nodes.add(com.example.sixthlesson.database.Node(nodes[nodes.size - 1].id + 1, value, emptyList()))
    }

    fun getSimpleNodeList(): MutableList<com.example.sixthlesson.database.Node> {
        return nodes
    }

    fun getSimpleNodeListById(id: Int): com.example.sixthlesson.database.Node {
        return nodes[id]
    }

    fun getSimpleNodeListWithoutItemId(id: Int): MutableList<com.example.sixthlesson.database.Node> {
        val tempNodes = mutableListOf<com.example.sixthlesson.database.Node>()
        for (i in nodes.indices) {
            if (id != i) tempNodes.add(nodes[i])
        }
        return tempNodes
    }

    fun getSimpleNodeListWithoutChildrenItemId(id: Int): MutableList<com.example.sixthlesson.database.Node> {
        val tempNodes = mutableListOf<com.example.sixthlesson.database.Node>()
        for (i in nodes.indices) {

            var iter = 0
            for (j in nodes[id].nodes.indices) {
                if (nodes[id].nodes[j].id == nodes[i].id) {
                    iter = 1
                    break
                }
            }
            if(iter == 0 && id != i) {
                tempNodes.add(nodes[i])
            }
        }

        return tempNodes
    }

    fun getSimpleNodeListWithoutParentItemId(id: Int): MutableList<com.example.sixthlesson.database.Node> {
        val tempNodes = mutableListOf<com.example.sixthlesson.database.Node>()
        for (i in nodes.indices) {
            var iter = 0
            for (j in nodes[i].nodes.indices) {
                if (nodes[i].nodes[j].id == id) {
                    iter = 1
                    break
                }
            }
            if(iter == 0 && id != i) {
                tempNodes.add(nodes[i])
            }

        }
        return tempNodes
    }

    fun setCardId(value: Int) {
        id = value
    }

    fun getCardId(): Int {
        return id
    }

}