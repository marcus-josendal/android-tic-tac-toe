package com.sc.marcus.tictactoev1

class GameAiEngine {

    /* Easy difficulty */
    fun makeMoveEasy(xArray: MutableList<Int>, oArray: MutableList<Int>): MutableList<Int> {

        var random: Int?
        while(oArray.size == oArray.size) {
            random = (1..9).random()
            if(!xArray.contains(random) && !oArray.contains(random)) {
                oArray.add(random)
                break
            }
        }
        return oArray
    }

    /*Medium difficulty */
    fun makeMoveMedium(xArray: MutableList<Int>, oArray: MutableList<Int>): MutableList<Int> {
        return oArray
    }

    /* Hard difficulty */
    fun makeMoveHard(xArray: MutableList<Int>, oArray: MutableList<Int>): MutableList<Int> {
        return oArray
    }

}