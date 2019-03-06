package com.sc.marcus.tictactoev1

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class GameEngineUnitTest {

    private lateinit var engine: GameEngine
    private lateinit var aiEngine: GameAiEngine
    private var playMode = "Ai"
    private var difficulty = "Medium"
    private val winningList = arrayOf(
        "123", "456", "789", /* Three lines horizontally starting left side top */
        "147", "258", "369", /* Three lines vertically starting upper left */
        "159", "951", /* Across starting upper left corner and bottom right */
        "357", "753", /* Across starting upper right corner and bottom left */
        "321", "654", "987", /* Three lines horizontally starting right side top */
        "741", "852", "963" /* Three lines vertically starting bottom right */)

    @Before
    fun beforeEach() {
        engine = GameEngine(playMode, difficulty)
        aiEngine = GameAiEngine()
    }

    @Test
    fun shouldReturnTrueIfEven() {
        assertTrue(engine.isEven())
        assertFalse(!engine.isEven())
    }

    @Test
    fun shouldReturnFalseIfStringIsBlank() {
        val testString = ""
        val testString2 = "X"

        assertTrue(engine.isClicked(testString))
        assertFalse(engine.isClicked(testString2))
    }

    @Test
    fun shouldReturnLargestArray() {
        val mutableArray1 = mutableListOf(1, 2)
        val mutableArray2 = mutableListOf(1, 2, 3)

        assertEquals(engine.returnLastTurn(mutableArray1, mutableArray2), mutableArray2)
    }
    @Test
    fun shouldReturnTrueIfWon() {
        // Winning
        val mutableArray1 = mutableListOf(1, 2, 3)
        val mutableArray2 = mutableListOf(1, 8, 4, 7)
        val mutableArray3 = mutableListOf(2, 7, 1, 5, 9)

        //Not winning
        val mutableArray4 = mutableListOf(1, 2, 6, 9)
        val mutableArray5 = mutableListOf(1, 6, 7)
        val mutableArray6 = mutableListOf(6, 1, 7)

        assertTrue(engine.checkIfWon(mutableArray1, winningList))
        assertTrue(engine.checkIfWon(mutableArray2, winningList))
        assertTrue(engine.checkIfWon(mutableArray3, winningList))
        assertFalse(engine.checkIfWon(mutableArray4, winningList))
        assertFalse(engine.checkIfWon(mutableArray5, winningList))
        assertFalse(engine.checkIfWon(mutableArray6, winningList))
    }

}
