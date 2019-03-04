package com.sc.marcus.tictactoev1

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class AiEngineUnitTest {
    private lateinit var aiEngine: GameAiEngine
    private val winningList = arrayOf(
        "123", "456", "789", /* Three lines horizontally starting left side top */
        "147", "258", "369", /* Three lines vertically starting upper left */
        "159", "951", /* Across starting upper left corner and bottom right */
        "357", "753", /* Across starting upper right corner and bottom left */
        "321", "654", "987", /* Three lines horizontally starting right side top */
        "741", "852", "963" /* Three lines vertically starting bottom right */)

    @Before
    fun beforeEach() {
        aiEngine = GameAiEngine()
    }

    @Test
    fun shouldReturnCorrectArrayAfterMediumMove() {
        val xArray1 = mutableListOf(1, 2)
        val oArray1 = mutableListOf(4, 5)
        val firstTestExpectedResult = mutableListOf(4, 5, 3)

        val xArray2 = mutableListOf(1, 2, 6, 7)
        val oArray2 = mutableListOf(4, 5, 8)
        val secondTestExpectedResult = mutableListOf(4, 5, 8, 3)

        val xArray3 = mutableListOf(1)
        val oArray3 = mutableListOf<Int>()
        val thirdTestExpectedResult = 1

        assertEquals(aiEngine.makeMoveMedium(xArray1, oArray1, winningList), firstTestExpectedResult)
        assertEquals(aiEngine.makeMoveMedium(xArray2, oArray2, winningList), secondTestExpectedResult)
        assertEquals(aiEngine.makeMoveMedium(xArray3, oArray3, winningList)!!.size, thirdTestExpectedResult)
    }

    @Test
    fun shouldReturnCorrectArrayAfterBlock() {
        val xArray1 = mutableListOf(1, 2)
        val oArray1 = mutableListOf(4, 5)
        val firstTestExpectedResult = mutableListOf(4, 5, 3)

        val xArray2 = mutableListOf(1, 2, 6, 7)
        val oArray2 = mutableListOf(4, 5, 8)
        val secondTestExpectedResult = mutableListOf(4, 5, 8, 3)

        val xArray3 = mutableListOf(1, 3)
        val oArray3 = mutableListOf(5)
        val thirdExpectedResult = mutableListOf(5, 2)

        assertEquals(aiEngine.makeBlock(xArray1, oArray1, winningList), firstTestExpectedResult)
        assertEquals(aiEngine.makeBlock(xArray2, oArray2, winningList), secondTestExpectedResult)
        assertEquals(aiEngine.makeBlock(xArray3, oArray3, winningList), thirdExpectedResult)
    }

    @Test
    fun shouldReturnArrayContainingWinningCombinationIfOCanWin() {
        val xArray1 = mutableListOf(1, 2, 8)
        val oArray1 = mutableListOf(4, 5)
        val firstTestExpectedResult = mutableListOf(4, 5, 6)

        val xArray2 = mutableListOf(1, 2, 4)
        val oArray2 = mutableListOf(3, 5)
        val secondTestExpectedResult = mutableListOf(3, 5, 7)

        val xArray3 = mutableListOf(6, 9, 1)
        val oArray3 = mutableListOf(3, 6)
        val thirdTestExpectedResult = null

        assertEquals(aiEngine.checkIfOCanWin(xArray1, oArray1, winningList), firstTestExpectedResult)
        assertEquals(aiEngine.checkIfOCanWin(xArray2, oArray2, winningList), secondTestExpectedResult)
        assertEquals(aiEngine.checkIfOCanWin(xArray3, oArray3, winningList), thirdTestExpectedResult)


    }
}