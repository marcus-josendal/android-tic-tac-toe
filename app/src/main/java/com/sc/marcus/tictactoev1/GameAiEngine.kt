package com.sc.marcus.tictactoev1

class GameAiEngine {

    val engine = GameEngine("foo", "bar")

    /* Easy difficulty - makes random moves. You're actually mentally challenged if you lose to this */
    fun makeMoveEasy(xArray: MutableList<Int>, oArray: MutableList<Int>): MutableList<Int> {
        println("retard move")
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

    fun makeMoveMedium(xArray: MutableList<Int>, oArray: MutableList<Int>, winningList: Array<String>): MutableList<Int> {

        if(xArray.size == 1) {
            return makeMoveEasy(xArray, oArray)
        } else if(xArray.size == 2) {
            return makeBlock(xArray, oArray, winningList)
        } else if(xArray.size == 3) {
            return makeBlock(xArray, oArray, winningList)
        } else if(xArray.size == 4) {
            return makeBlock(xArray, oArray, winningList)
        }
        return oArray
    }

    fun makeBlock(xArray: MutableList<Int>, oArray: MutableList<Int>, winningList: Array<String>): MutableList<Int> {
        var counter = 0

        for(i in winningList.indices) {
            counter++
            if(counter == winningList.size - 1) {
                return makeMoveEasy(xArray, oArray)
            }
            for(j in winningList[i].toCharArray().indices) {
                when {
                    blockerPosition(xArray, winningList, i, oArray, 0, 1, 2) -> return oArray
                    blockerPosition(xArray, winningList, i, oArray, 0, 2, 1) -> return oArray
                    blockerPositionEdgeCase(xArray, oArray, 6, 9, 3) -> return oArray
                    blockerPositionEdgeCase(xArray, oArray, 5, 8, 2) -> return oArray
                }
            }
        }
        return oArray
    }

    private fun blockerPosition(xArray: MutableList<Int>, winningList: Array<String>, i: Int, oArray: MutableList<Int>, pos1: Int, pos2: Int, blockPos: Int): Boolean {
        if (xArray.contains(makeInt(winningList, i, pos1)) && xArray.contains(makeInt(winningList, i, pos2))) {
            val blocker = makeInt(winningList, i, blockPos)
            if (!xArray.contains(blocker) && !oArray.contains(blocker)) {
                oArray.add(blocker)
                return true
            }
        }
        return false
    }

    private fun blockerPositionEdgeCase(xArray: MutableList<Int>, oArray: MutableList<Int>, pos1: Int, pos2: Int, blockPos: Int): Boolean {
        if (xArray.contains(pos1) && xArray.contains(pos2)) {
            if (!xArray.contains(blockPos) && !oArray.contains(blockPos)) {
                oArray.add(blockPos)
                return true
            }
        }
        return false
    }

    /* Returns an int that is an index of a char array that is an array index of a String array - Used for shortening code */
    private fun makeInt(array: Array<String>, pos1: Int, pos2: Int): Int {

        val array1 = mutableListOf<String>()
        array.map { number ->
            array1.add(number)
        }
        return array1[pos1].toCharArray()[pos2].toString().toInt()
    }

}