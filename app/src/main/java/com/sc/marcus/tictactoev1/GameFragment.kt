package com.sc.marcus.tictactoev1

import android.annotation.SuppressLint
import android.net.Uri
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

        //Toast.makeText(context, arguments?.getString("playMode") ?: "Nothing", Toast.LENGTH_LONG).show()

        val test = arguments.toString()

        val engine = GameEngine(test)
        val btnArray = arrayListOf<Button>(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)

        btn1.setOnClickListener{
            checkGameLogic(btn1, 1, engine)
            checkWin(engine, btnArray)
        }

        btn2.setOnClickListener{
            checkGameLogic(btn2, 2, engine)
            checkWin(engine, btnArray)
        }

        btn3.setOnClickListener{
            checkGameLogic(btn3, 3, engine)
            checkWin(engine, btnArray)
        }

        btn4.setOnClickListener{
            checkGameLogic(btn4, 4, engine)
            checkWin(engine, btnArray)
        }

        btn5.setOnClickListener{
            checkGameLogic(btn5, 5, engine)
            checkWin(engine, btnArray)
        }

        btn6.setOnClickListener{
            checkGameLogic(btn6, 6, engine)
            checkWin(engine, btnArray)
        }

        btn7.setOnClickListener{
            checkGameLogic(btn7, 7, engine)
            checkWin(engine, btnArray)
        }

        btn8.setOnClickListener{
            checkGameLogic(btn8, 8, engine)
            checkWin(engine, btnArray)
        }

        btn9.setOnClickListener{
            checkGameLogic(btn9, 9, engine)
            checkWin(engine, btnArray)
        }

        btnReset.setOnClickListener{
            resetGame(engine, btnArray)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }


    private fun checkGameLogic(btnName: Button, btnNumber: Int, engine: GameEngine) {
        val buttonContent = btnName.text.toString()

        if(engine.isEven() && engine.isClicked(buttonContent)) {
            btnName.text = "X"
            engine.updateArray(btnNumber)
        } else if (engine.isClicked(buttonContent)) {
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
