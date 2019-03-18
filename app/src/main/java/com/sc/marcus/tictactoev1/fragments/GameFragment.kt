package com.sc.marcus.tictactoev1.fragments

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.sc.marcus.tictactoev1.gamelogic.GameEngine
import com.sc.marcus.tictactoev1.R
import com.sc.marcus.tictactoev1.database.GameViewModel
import com.sc.marcus.tictactoev1.database.Player
import kotlinx.android.synthetic.main.fragment_game.*
import java.lang.Exception

class GameFragment : Fragment() {

    private val winningList = arrayOf(
        "123", "456", "789", /* Three lines horizontally starting left side top */
        "147", "258", "369", /* Three lines vertically starting upper left */
        "159", "951", /* Across starting upper left corner and bottom right */
        "357", "753", /* Across starting upper right corner and bottom left */
        "321", "654", "987", /* Three lines horizontally starting right side top */
        "741", "852", "963" /* Three lines vertically starting bottom right */)

    private var btnArray = arrayListOf<Button>()
    lateinit var viewModel: GameViewModel
    lateinit var playMode: String
    lateinit var player1: Player
    lateinit var player2: Player

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(GameViewModel::class.java)
        } ?: throw Exception("Invalid Activity") as Throwable

        playMode = arguments?.getString("playMode").toString()
        val difficulty = arguments?.getString("difficulty").toString()
        player1 = arguments?.getParcelable("player") ?: kotlin.run {
            throw Exception("This should never happen")
        }
        player2 = arguments?.getParcelable("player2")!!
        timer.start()

        //Toast.makeText(context, "Playmode: $playMode Difficulty: $difficulty Player1: $player1 Player2: $player2", Toast.LENGTH_LONG).show()

        val engine = GameEngine(playMode, difficulty)
        btnArray = arrayListOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)

        btn1.setOnClickListener{
            checkGameLogic(btn1, 1, engine, playMode)
            checkWin(engine, btnArray)
        }

        btn2.setOnClickListener{
            checkGameLogic(btn2, 2, engine, playMode)
            checkWin(engine, btnArray)
        }

        btn3.setOnClickListener{
            checkGameLogic(btn3, 3, engine, playMode)
            checkWin(engine, btnArray)
        }

        btn4.setOnClickListener{
            checkGameLogic(btn4, 4, engine, playMode)
            checkWin(engine, btnArray)
        }

        btn5.setOnClickListener{
            checkGameLogic(btn5, 5, engine, playMode)
            checkWin(engine, btnArray)
        }

        btn6.setOnClickListener{
            checkGameLogic(btn6, 6, engine, playMode)
            checkWin(engine, btnArray)
        }

        btn7.setOnClickListener{
            checkGameLogic(btn7, 7, engine, playMode)
            checkWin(engine, btnArray)
        }

        btn8.setOnClickListener{
            checkGameLogic(btn8, 8, engine, playMode)
            checkWin(engine, btnArray)
        }

        btn9.setOnClickListener{
            checkGameLogic(btn9, 9, engine, playMode)
            checkWin(engine, btnArray)
        }

        btnReset.setOnClickListener{
            resetGame(engine, btnArray)
        }

        btnHighscore.setOnClickListener{
            findNavController().navigate(R.id.action_gameFragment_to_playerFragment)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }


    private fun checkGameLogic(btnName: Button, btnNumber: Int, engine: GameEngine, playMode: String) {
        val buttonContent = btnName.text.toString()
        if(engine.isEven() && engine.isClicked(buttonContent)) {
            btnName.text = "X"
            engine.updateArray(btnNumber)
            if(playMode == "Ai"){
                val aiBtnNumber = engine.aiMove()
                when (aiBtnNumber) {
                    1 -> btn1.text = "O"
                    2 -> btn2.text = "O"
                    3 -> btn3.text = "O"
                    4 -> btn4.text = "O"
                    5 -> btn5.text = "O"
                    6 -> btn6.text = "O"
                    7 -> btn7.text = "O"
                    8 -> btn8.text = "O"
                    9 -> btn9.text = "O"
                }
            }
        } else if (engine.isClicked(buttonContent) && playMode == "Versus") {
            btnName.text = "O"
            engine.updateArray(btnNumber)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun checkWin(engine: GameEngine, btnArray: ArrayList<Button>) {
        val xArray = engine.returnXArray()
        val oArray = engine.returnOArray()

        when {
            engine.checkIfWon(xArray, winningList) -> {
                winnerText.text = "X won!"
                disableButtonsAndClock(btnArray)
                viewModel.updateScoreByName(++player1.score, player1.name)
            }
            engine.checkIfWon(oArray, winningList) -> {
                winnerText.text = "O won!"
                disableButtonsAndClock(btnArray)
                viewModel.updateScoreByName(++player2.score, player2.name)
            }
            engine.returnOArray().size == 4 && engine.returnXArray().size == 5 && !engine.checkIfWon(oArray, winningList) && !engine.checkIfWon(xArray, winningList) -> {
                winnerText.text = "Draw!"
                disableButtonsAndClock(btnArray)
            }
        }
    }

    private fun disableButtonsAndClock(btnArray: ArrayList<Button>) {
        timer.stop()
        btnArray.map { button ->
            button.isEnabled = false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun resetGame(engine: GameEngine, btnArray: ArrayList<Button>) {
        winnerText.text = "Waiting for result.."
        timer.base = SystemClock.elapsedRealtime()
        btnArray.map { button ->
            button.text = ""
            button.isEnabled = true
        }
        engine.resetGame()
    }
}
