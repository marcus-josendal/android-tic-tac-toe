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
    fun returnCorrectEndingNumber() {
        val listItem1 = "12"
    }
}