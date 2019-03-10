package com.sc.marcus.tictactoev1

class GameAiEngine {


    /* Easy difficulty - makes random moves. You're actually mentally challenged if you lose to this */
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

    /* Medium difficulty - if O can win it makes a winning move and if it can block X it will block.
       if neither are true it will do a random move. This way you can still trick it with "two
       dimensional" combinations
     */
    fun makeMoveMedium(xArray: MutableList<Int>, oArray: MutableList<Int>, winningList: Array<String>): MutableList<Int>? {

        val tmpOArray = checkIfOCanWin(xArray, oArray, winningList)
        return when {
            xArray.size == 1 -> makeMoveEasy(xArray, oArray)
            xArray.size == 2 -> tmpOArray ?: makeBlock(xArray, oArray, winningList)
            xArray.size == 3 -> tmpOArray ?: makeBlock(xArray, oArray, winningList)
            xArray.size == 4 -> tmpOArray ?: makeBlock(xArray, oArray, winningList)
            else -> oArray
        }
    }
    /* Impossible to beat - The first two moves are hardcoded to ensure that it is not possible to win
       the remaining logic is the same as the medium difficulty.
     */
    fun makeMoveHard(xArray: MutableList<Int>, oArray: MutableList<Int>, winningList: Array<String>): MutableList<Int>? {

        val tmpOArray = checkIfOCanWin(xArray, oArray, winningList)
        when {
            xArray.size == 1 -> {
                oArray.add(firstOMoveHard(xArray)!!)
                return oArray
            }
            xArray.size == 2 -> when {
                secondMoveEdgeCase(xArray) && !oArray.contains(5) -> {
                    oArray.add(5)
                    return oArray
                }
                xArray.contains(1) && xArray.contains(9) -> oArray.add(6)
                xArray.contains(7) && xArray.contains(3) -> oArray.add(4)
                tmpOArray != null -> return tmpOArray
                xArray[1] == 9 && !xArray.contains(7) && !xArray.contains(3) -> {
                    oArray.add(3)
                    return oArray
                }
                else -> return makeBlock(xArray, oArray, winningList)
            }
            xArray.size == 3 -> return tmpOArray ?: makeBlock(xArray, oArray, winningList)
            xArray.size == 4 -> return tmpOArray ?: makeBlock(xArray, oArray, winningList)
        }
        return oArray
    }

    /* First O-move for hard difficulty */
    fun firstOMoveHard(xArray: MutableList<Int>): Int? {

        return when {
            xArray.contains(1) || xArray.contains(3) || xArray.contains(7) || xArray.contains(9) -> 5
            xArray.contains(4) -> 1
            xArray.contains(6) -> 3
            xArray.contains(2) -> 1
            xArray.contains(5) -> 1
            xArray.contains(8) -> 7
            else -> null
        }
    }

    /* Returns false if xArray does not contain 5 */
    fun secondMoveEdgeCase(xArray: MutableList<Int>): Boolean {
        return !xArray.contains(5)
    }

    /* Makes block on both ending tiles and middle tiles
       If it cannot make a block it will do a random move
    */
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
                    blockerPositionEdgeCase(xArray, oArray, 4, 7, 1) -> return oArray
                }
            }
        }
        return oArray
    }

    /* Checks if O can win. If it can win it will return a winning array, if not it will return null */
    fun checkIfOCanWin(xArray: MutableList<Int>, oArray: MutableList<Int>, winningList: Array<String>): MutableList<Int>? {
        var counter = 0

        for(i in winningList.indices) {
            counter++
            if(counter == winningList.size - 1) {
                return null
            }
            for(j in winningList[i].toCharArray().indices) {
                if(oArray.contains(makeInt(winningList, i, 0)) && oArray.contains(makeInt(winningList, i, 1))) {
                    val move = makeInt(winningList, i, 2)
                    if (!xArray.contains(move) && !oArray.contains(move)) {
                        oArray.add(move)
                        return oArray
                    }
                }
                if(oArray.contains(makeInt(winningList, i, 0)) && oArray.contains(makeInt(winningList, i, 2))) {
                    val move = makeInt(winningList, i, 1)
                    if (!xArray.contains(move) && !oArray.contains(move)) {
                        oArray.add(move)
                        return oArray
                    }
                }
            }
        }
        return null
    }

    /* Returns true of a position in the xArray can be blocked */
    fun blockerPosition(xArray: MutableList<Int>, winningList: Array<String>, index: Int, oArray: MutableList<Int>, pos1: Int, pos2: Int, blockPos: Int): Boolean {
        if (xArray.contains(makeInt(winningList, index, pos1)) && xArray.contains(makeInt(winningList, index, pos2))) {
            val blocker = makeInt(winningList, index, blockPos)
            if (!xArray.contains(blocker) && !oArray.contains(blocker)) {
                oArray.add(blocker)
                return true
            }
        }
        return false
    }

    /* Some blocking combinations didn't work somehow. Had to make this to handle the three edgecases */
    fun blockerPositionEdgeCase(xArray: MutableList<Int>, oArray: MutableList<Int>, pos1: Int, pos2: Int, blockPos: Int): Boolean {
        if (xArray.contains(pos1) && xArray.contains(pos2)) {
            if (!xArray.contains(blockPos) && !oArray.contains(blockPos)) {
                oArray.add(blockPos)
                return true
            }
        }
        return false
    }

    /* Returns an int that is an index of a char array that is an array index of a String array - Used for shortening code */
    fun makeInt(array: Array<String>, pos1: Int, pos2: Int): Int {

        val array1 = mutableListOf<String>()
        array.map { number ->
            array1.add(number)
        }
        return array1[pos1].toCharArray()[pos2].toString().toInt()
    }

}