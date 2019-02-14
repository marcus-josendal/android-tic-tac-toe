package com.sc.marcus.tictactoev1

class GameAiEngine {

    /* Easy difficulty */
    fun makeMove(xArray: MutableList<Int>, oArray: MutableList<Int>): MutableList<Int> {
        val tempOArray = oArray

        var random: Int?
        while(tempOArray.size == oArray.size) {
            random = (1..9).random()
            if(!xArray.contains(random) && !oArray.contains(random)) {
                oArray.add(random)
                break
            }
        }
        return oArray
    }

    /*Medium difficulty */
    fun makeMoveMedium(xArray: MutableList<Int>, oArray: MutableList<Int>) {

    }

}