package com.sc.marcus.tictactoev1

import com.sc.marcus.tictactoev1.gamelogic.GameAiEngine
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
    fun shouldAddRandomMoveAndReturnOneIndexLongerArrayAfterEasyMove() {
        val xArray1 = mutableListOf(1, 2, 4)
        val oArray1 = mutableListOf(5, 6)
        val firstTestExpectedResult = 3

        val xArray2 = mutableListOf(1, 2, 6, 7)
        val oArray2 = mutableListOf(4, 5, 8)
        val secondTestExpectedResult = 4

        assertEquals(aiEngine.makeMoveEasy(xArray1, oArray1).size, firstTestExpectedResult)
        assertEquals(aiEngine.makeMoveEasy(xArray2, oArray2).size, secondTestExpectedResult)
    }

    @Test
    fun shouldReturnCorrectArrayAfterMediumMove() {
        val xArray1 = mutableListOf(1, 2, 4)
        val oArray1 = mutableListOf(5, 6)
        val firstTestExpectedResult = mutableListOf(5, 6, 3)

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
    fun shouldReturnCorrectArrayAfterHardMove() {
        val xArray1 = mutableListOf(1, 7, 3)
        val oArray1 = mutableListOf(5, 4)
        val firstTestExpectedResult = mutableListOf(5, 4, 6)

        val xArray2 = mutableListOf(5, 2, 6, 7)
        val oArray2 = mutableListOf(1, 8, 4)
        val secondTestExpectedResult = mutableListOf(1, 8, 4, 3)

        val xArray3 = mutableListOf(4, 5, 8, 9)
        val oArray3 = mutableListOf(1, 6, 2)
        val thirdTestExpectedResult = mutableListOf(1, 6, 2, 3)

        val xArray4 = mutableListOf(8, 2, 3, 4)
        val oArray4 = mutableListOf(7, 5, 1)
        val fourthTestExpectedResult = mutableListOf(7, 5, 1, 9)

        val xArray5 = mutableListOf(2, 3, 9, 4, 7)
        val oArray5 = mutableListOf(1, 5, 6, 8)
        val fifthTestExpectedResult = mutableListOf(1, 5, 6, 8)

        assertEquals(aiEngine.makeMoveHard(xArray1, oArray1, winningList), firstTestExpectedResult)
        assertEquals(aiEngine.makeMoveHard(xArray2, oArray2, winningList), secondTestExpectedResult)
        assertEquals(aiEngine.makeMoveHard(xArray3, oArray3, winningList), thirdTestExpectedResult)
        assertEquals(aiEngine.makeMoveHard(xArray4, oArray4, winningList), fourthTestExpectedResult)
        assertEquals(aiEngine.makeMoveHard(xArray5, oArray5, winningList), fifthTestExpectedResult)
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

    @Test
    fun shouldDoCorrectMoveHard() {

        val xArray1 = mutableListOf(1)
        val firstTestExpectedResult = 5

        val xArray2 = mutableListOf(9)
        val secondTestExpectedResult = 5

        val xArray3 = mutableListOf(4)
        val thirdTestExpectedResult = 1

        val xArray4 = mutableListOf(6)
        val fourthTestExpectedResult = 3

        val xArray5 = mutableListOf(2)
        val fifthTestExpectedResult = 1

        val xArray6 = mutableListOf(5)
        val sixthTestExpectedResult = 1

        val xArray7 = mutableListOf(8)
        val seventhTestExpectedResult = 7

        assertEquals(aiEngine.firstOMoveHard(xArray1), firstTestExpectedResult)
        assertEquals(aiEngine.firstOMoveHard(xArray2), secondTestExpectedResult)
        assertEquals(aiEngine.firstOMoveHard(xArray3), thirdTestExpectedResult)
        assertEquals(aiEngine.firstOMoveHard(xArray4), fourthTestExpectedResult)
        assertEquals(aiEngine.firstOMoveHard(xArray5), fifthTestExpectedResult)
        assertEquals(aiEngine.firstOMoveHard(xArray6), sixthTestExpectedResult)
        assertEquals(aiEngine.firstOMoveHard(xArray7), seventhTestExpectedResult)
    }

    @Test
    fun shouldReturnTrueIfXArrayContainsFive() {
        assertFalse(aiEngine.secondMoveEdgeCase(mutableListOf(5)))
        assertTrue(aiEngine.secondMoveEdgeCase(mutableListOf(1)))
    }


    @Test
    fun shouldReturnCorrectNumberFromWinningCombinationIndex() {
        assertEquals(aiEngine.makeInt(winningList, 0, 0), 1)
        assertEquals(aiEngine.makeInt(winningList, 1, 2), 6)
    }

    @Test
    fun shouldReturnTrueIfItCanBlockEdgeCase() {
        val xArray1 = mutableListOf(6, 9)
        val oArray1 = mutableListOf(4)

        val xArray2 = mutableListOf(1, 4)
        val oArray2 = mutableListOf(5)

        val xArray3 = mutableListOf(2, 3)
        val oArray3 = mutableListOf(1)

        assertTrue(aiEngine.blockerPositionEdgeCase(xArray1, oArray1, 6, 9, 3))
        assertTrue(aiEngine.blockerPositionEdgeCase(xArray2, oArray2, 1, 4, 7))
        assertFalse(aiEngine.blockerPositionEdgeCase(xArray3, oArray3, 2, 3, 1))
    }

    @Test
    fun shouldReturnTrueIfItCanBlockPosition() {
        val xArray1 = mutableListOf(1, 2)
        val oArray1 = mutableListOf(4)

        val xArray2 = mutableListOf(1, 3)
        val oArray2 = mutableListOf(4)

        assertTrue(aiEngine.blockerPosition(xArray1, winningList, 0, oArray1, 0, 1, 2))
        assertTrue(aiEngine.blockerPosition(xArray2, winningList, 0, oArray2, 0, 2, 1))
    }




}