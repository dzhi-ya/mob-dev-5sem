package com.example.thirdlesson

class Calculator {
    companion object{
        var firstVal: Double? = null
        var secondVal: Double? = null
        var operation: String = ""
        fun result(): Double? {
            var result: Double? = null
            if(firstVal != null && secondVal != null) {
                when (operation) {
                    "+" -> result = firstVal!! + secondVal!!
                    "-" -> result = firstVal!! - secondVal!!
                    "*" -> result = firstVal!! * secondVal!!
                    "/" -> result = firstVal!! / secondVal!!
                }
            }
            return result
        }
    }
}