package com.sc.marcus.tictactoev1.gamelogic

class GameEngine(playMode: String?, difficulty: String?) {

    private var aiEngine: GameAiEngine? = null
    private var diff: String? = null
    private var xArray = mutableListOf<Int>()
    private var oArray = mutableListOf<Int>()
    private var turnTracker = 0

    private val winningList = arrayOf(
        "123", "456", "789", /* Three lines horizontally starting left side top */
        "147", "258", "369", /* Three lines vertically starting upper left */
        "159", "951", /* Across starting upper left corner and bottom right */
        "357", "753", /* Across starting upper right corner and bottom left */
        "321", "654", "987", /* Three lines horizontally starting right side top */
        "741", "852", "963" /* Three lines vertically starting bottom right */)

    init {
        if(playMode == "Ai") {
            aiEngine = GameAiEngine()
            when(difficulty) {
                "Easy" -> diff = "Easy"
                "Medium" -> diff = "Medium"
                "Hard" -> diff = "Hard"
            }
        }
    }

    /* Checks if number is even or odd */
    fun isEven(): Boolean {
        return turnTracker % 2 == 0
    }

    /* Updates the players array after every move and checks if player has won */
    fun updateArray(buttonPos: Int) {
        if(turnTracker % 2 == 0) {
            xArray.add(buttonPos)
            checkIfWon(xArray, winningList)
        }
        else {
            oArray.add(buttonPos)
            checkIfWon(oArray, winningList)
        }
        turnTracker++
    }

    /* Returns array which did the last move */
    fun returnLastTurn(xArray: MutableList<Int>, oArray: MutableList<Int>): MutableList<Int>? {
        if(xArray.size > oArray.size) {
            return xArray
        } else if (oArray.size > xArray.size) {
            return oArray
        }
        return null
    }

    /* Makes sure a button that has already been clicked is not clicked again */
    fun isClicked(buttonText: String): Boolean {
        return !buttonText.isNotBlank()
    }

    /* Returns copy of array of player X */
    fun returnXArray(): MutableList<Int> {
        return xArray
    }

    /* Returns copy of array of player O */
    fun returnOArray(): MutableList<Int> {
        return oArray
    }

    /* Checks player combination against possible winning combinations */
    fun checkIfWon(playerList: MutableList<Int>, winningList: Array<String>): Boolean {
        if (playerList.isEmpty()) return false
        var counter: Int
        val temp = mutableListOf<Int>()

        for(combination in winningList) {
            counter = 0
            combination.toCharArray().forEach { number ->
                temp.add(number.toString().toInt())
            }

            for(i in playerList.indices) {
                for(j in temp.indices) {
                    if(temp[j] == playerList[i]) {
                        counter++
                    }
                }
            }
            temp.clear()
            if(counter == 3) return true
        }
        return false
    }

    fun resetGame() {
        xArray.clear()
        oArray.clear()
        turnTracker = 0
    }

    fun aiMove(): Int? {
        turnTracker++
        val tempOArray: MutableList<Int>?
        when {
            turnTracker <= 9 && diff == "Easy" -> {
                tempOArray = aiEngine?.makeMoveEasy(returnXArray(), returnOArray())
                return tempOArray!![tempOArray.size - 1]
            }
            turnTracker <= 9 && diff == "Medium" -> {
                tempOArray = aiEngine?.makeMoveMedium(returnXArray(), returnOArray(), winningList)
                println(tempOArray)
                return tempOArray!![tempOArray.size - 1]
            }
            turnTracker <= 9 && diff == "Hard" -> {
                tempOArray = aiEngine?.makeMoveHard(returnXArray(), returnOArray(), winningList)
                return tempOArray!![tempOArray.size - 1]
            }
            else -> return null
        }
    }
}