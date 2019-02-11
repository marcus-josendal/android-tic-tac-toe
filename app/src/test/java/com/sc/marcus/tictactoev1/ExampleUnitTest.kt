package com.sc.marcus.tictactoev1

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ExampleUnitTest {

    private lateinit var engine: GameEngine

    @Before
    fun beforeEach() {
        engine = GameEngine(playMode)
    }

    @Test
    fun shouldReturnTrueIfEven() {
        assertTrue(engine.isEven())
        assertFalse(engine.isEven())
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
}
