package com.sc.marcus.tictactoev1

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(context, arguments?.getString("playMode") ?: "Nothing", Toast.LENGTH_SHORT).show()

        val playMode = arguments?.getString("playMode").toString()

        val engine = GameEngine(playMode)
        val btnArray = arrayListOf<Button>(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)

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
                println("ai move")
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
            println("O move")
            btnName.text = "O"
            engine.updateArray(btnNumber)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkWin(engine: GameEngine, btnArray: ArrayList<Button>) {
        val xArray = engine.returnXArray()
        val oArray = engine.returnOArray()

        if(engine.checkIfWon(xArray)) {
            winnerText.text = "X won!"
            disableButtons(btnArray)
        } else if(engine.checkIfWon(oArray)) {
            winnerText.text = "O won!"
            disableButtons(btnArray)
        //TODO: Check draw in a more fashionable manner
        } else if(engine.returnOArray().size == 4 && engine.returnXArray().size == 5 && !engine.checkIfWon(oArray) && !engine.checkIfWon(xArray)) {
            winnerText.text = "Draw!"
            disableButtons(btnArray)
        }
    }

    private fun disableButtons(btnArray: ArrayList<Button>) {
        btnArray.map { button ->
            button.isEnabled = false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun resetGame(engine: GameEngine, btnArray: ArrayList<Button>) {
        winnerText.text = "Waiting for result.."
        btnArray.map { button ->
            button.text = ""
            button.isEnabled = true
        }
        engine.resetGame()
    }
}
