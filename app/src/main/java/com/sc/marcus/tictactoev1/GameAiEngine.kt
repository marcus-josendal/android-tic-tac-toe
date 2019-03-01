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


    /*
    /*Medium difficulty */
    fun makeMoveMedium(xArray: MutableList<Int>, oArray: MutableList<Int>, winningList: Array<String>): MutableList<Int> {

        val temp = checkIfOCanWin(oArray, winningList)

        if(xArray.size == 1 || xArray.size == 2 && oArray.size > 1) {
            return makeMoveEasy(xArray, oArray)
        }

        if(xArray.size == 2) {
            return if(checkIfMiddleTitleOrEndingTile(xArray, winningList)) {
                blockTileMiddle(xArray, oArray, winningList)
            } else {
                blockTileEnd(xArray, oArray, winningList)
            }
        }
        if(xArray.size == 3) {

            return if(engine.checkIfWon(temp, winningList)) {
                temp
            } else if (checkIfMiddleTitleOrEndingTile(xArray, winningList)) {
                blockTileMiddle(xArray, oArray, winningList)
            } else {
                blockTileEnd(xArray, oArray, winningList)
            }
        }
        if(xArray.size == 4) {
            return if(engine.checkIfWon(temp, winningList)) {
                temp
            } else if (checkIfMiddleTitleOrEndingTile(xArray, winningList)) {
                blockTileMiddle(xArray, oArray, winningList)
            } else {
                blockTileEnd(xArray, oArray, winningList)
            }
        }
        if(xArray.size == 5) {
            return oArray
        }
        return oArray
    }

    fun checkIfOCanWin(oArray: MutableList<Int>, winningList: Array<String>): MutableList<Int> {

        var move = 0

        for(i in winningList.indices) {
            for(j in winningList[i].toCharArray()) {
                if(oArray.contains(winningList[i].toCharArray()[0].toString().toInt()) && oArray.contains(winningList[i].toCharArray()[1].toString().toInt())) {
                    move = winningList[i].toCharArray()[2].toString().toInt()
                } else if(oArray.contains(winningList[i].toCharArray()[2].toString().toInt()) && oArray.contains(winningList[i].toCharArray()[1].toString().toInt())) {
                    move = winningList[i].toCharArray()[0].toString().toInt()
                } else if(oArray.contains(winningList[i].toCharArray()[0].toString().toInt()) && oArray.contains(winningList[i].toCharArray()[2].toString().toInt())) {
                    move = winningList[i].toCharArray()[1].toString().toInt()
                } else if(oArray.contains(winningList[i].toCharArray()[2].toString().toInt()) && oArray.contains(winningList[i].toCharArray()[0].toString().toInt())) {
                    move = winningList[i].toCharArray()[1].toString().toInt()
                }
            }
        }
        oArray.add(move)
        return oArray
    }


    fun makeMoveHard(xArray: MutableList<Int>, oArray: MutableList<Int>, winningList: Array<String>): MutableList<Int> {
        return oArray
    }

    /* Blocks rows that are missing a tile in the middle */
    fun blockTileMiddle(xArray: MutableList<Int>, oArray: MutableList<Int>, winningList: Array<String>): MutableList<Int> {
        val startSize = oArray.size
        var counter = 0

        var blocker: Int
        while (oArray.size == startSize) {
            for (i in winningList.indices) {
                counter++
                if(counter == winningList.size - 1) {
                    return makeMoveEasy(xArray, oArray)
                }
                for (j in winningList[i].toCharArray().indices) {
                    if(xArray.contains(winningList[i].toCharArray()[0].toString().toInt()) && xArray.contains(winningList[i].toCharArray()[2].toString().toInt())) {
                        blocker = if(returnCorrectNumberMiddle(xArray)) {
                            5
                        } else {
                            winningList[i].toCharArray()[1].toString().toInt()
                        }
                        if(!oArray.contains(blocker) && !xArray.contains(blocker)) {
                            println("Smart boye middle")
                            oArray.add(blocker)
                            return oArray
                        }
                    }
                }
            }
        }
        return oArray
    }

    fun blockTileEnd(xArray: MutableList<Int>, oArray: MutableList<Int>, winningList: Array<String>): MutableList<Int> {
        val startSize = oArray.size
        var counter = 0

        var blocker: Int
        while (oArray.size == startSize) {
            for (i in winningList.indices) {
                counter++
                if(counter == winningList.size - 1) {
                    return makeMoveEasy(xArray, oArray)
                }
                for (j in winningList[i].toCharArray().indices) {
                    if((xArray.contains(winningList[i].toCharArray()[0].toString().toInt()) && xArray.contains(winningList[i].toCharArray()[1].toString().toInt())) ||
                        (xArray.contains(winningList[i].toCharArray()[2].toString().toInt()) && xArray.contains(winningList[i].toCharArray()[1].toString().toInt()))) {
                        blocker = winningList[i].toCharArray()[returnCorrectNumberEnd(winningList[i], xArray)].toString().toInt()
                        if(!oArray.contains(blocker) && !xArray.contains(blocker)) {
                            println("Smart boye end")
                            oArray.add(blocker)
                            return oArray
                        }
                    }
                }
            }
        }
        return oArray
    }

    private fun returnCorrectNumberEnd(listItem: String, xArray: MutableList<Int>): Int {
        for(i in listItem.toCharArray().indices) {
            return if(xArray.contains(listItem.toCharArray()[0].toString().toInt()) && xArray.contains(listItem.toCharArray()[1].toString().toInt())) {
                2
            } else {
                0
            }
        }
       return 0
    }

    private fun returnCorrectNumberMiddle(xArray: MutableList<Int>): Boolean {
        if((xArray.contains(1) && xArray.contains(9)) || (xArray.contains(3) && xArray.contains(7))) {
            return true
        }
        return false
    }

    /*
        Checks if X is either about to win with a missing tile in the middle (X - X) or at the end (X X -).
        Returns true of middle tile is missing and false if ending tile is missing. This method does not check
        if the number already exist in the oArray.
    */
    fun checkIfMiddleTitleOrEndingTile (xArray: MutableList<Int>, winningList: Array<String>): Boolean {

        for(i in winningList.indices) {
            for(j in winningList[i].toCharArray().indices) {
                if(xArray.contains(winningList[i].toCharArray()[0].toString().toInt()) && xArray.contains(winningList[i].toCharArray()[2].toString().toInt())) {
                    return true
                } else if((xArray.contains(winningList[i].toCharArray()[0].toString().toInt()) && xArray.contains(winningList[i].toCharArray()[1].toString().toInt())) ||
                    (xArray.contains(winningList[i].toCharArray()[2].toString().toInt()) && xArray.contains(winningList[i].toCharArray()[1].toString().toInt()))) {
                    return false
                }
            }
        }
        return false
    } */

}