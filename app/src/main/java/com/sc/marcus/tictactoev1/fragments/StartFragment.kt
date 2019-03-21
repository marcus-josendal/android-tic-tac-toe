package com.sc.marcus.tictactoev1.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.sc.marcus.tictactoev1.R
import com.sc.marcus.tictactoev1.database.GameViewModel
import com.sc.marcus.tictactoev1.database.Player
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment() {

    var difficulty: String? = null
    var gameMode: String? = null
    lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(GameViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        player2Name.isEnabled = false
        btnEasy.isEnabled = false
        btnMedium.isEnabled = false
        btnGow.isEnabled = false

        fun setButtonsInitial(btnArray: List<Button>) {
            btnArray.map{ btn ->
                btn.setBackgroundColor(Color.WHITE)
                btn.setTextColor(Color.BLACK)
                btn.setBackgroundResource(R.drawable.btn_border)
            }
        }

        fun setButtonClicked(btn: Button) {
            btn.setBackgroundColor(Color.BLACK)
            btn.setTextColor(Color.WHITE)
        }

        fun setButtonsDisabledStyle(btnArray: List<Button>) {
            btnArray.forEach { button ->
                button.setBackgroundResource(R.drawable.btn_border_disabled)
                button.setTextColor(Color.GRAY)
                button.isEnabled = false
            }
        }

        btnStartGame.isEnabled = false
        setButtonsDisabledStyle(listOf(btnStartGame))

        btnVs.setOnClickListener {
            setButtonsInitial(listOf(btnAi, btnEasy, btnMedium, btnGow, btnStartGame))
            setButtonClicked(btnVs)
            setButtonsDisabledStyle(listOf(btnEasy, btnMedium, btnGow))
            difficulty = null

            player2Name.isEnabled = true
            btnStartGame.isEnabled = true

            gameMode = "Versus"
        }

        btnAi.setOnClickListener {
            setButtonsInitial(listOf(btnVs, btnEasy, btnMedium, btnGow))
            setButtonsDisabledStyle(listOf(btnStartGame))
            setButtonClicked(btnAi)

            btnEasy.isEnabled = true
            btnMedium.isEnabled = true
            btnGow.isEnabled = true
            player2Name.isEnabled = false
            gameMode = "Ai"
        }

        btnEasy.setOnClickListener {
            setButtonClicked(btnEasy)
            setButtonsInitial(listOf(btnMedium, btnGow, btnStartGame))

            difficulty = "Easy"
            btnStartGame.isEnabled = true
        }

        btnMedium.setOnClickListener {
            setButtonsInitial(listOf(btnEasy, btnGow, btnStartGame))
            setButtonClicked(btnMedium)

            difficulty = "Medium"
            btnStartGame.isEnabled = true
        }

        btnGow.setOnClickListener {
            setButtonsInitial(listOf(btnEasy, btnMedium, btnStartGame))
            setButtonClicked(btnGow)

            difficulty = "Hard"
            btnStartGame.isEnabled = true
        }

        btnStartGame.setOnClickListener {

            val player1Name = player1Name.text.toString()
            val player2Name = player2Name.text.toString()
            val players = viewModel.allPlayersAndScoreSync.get()

            if(player1Name.isNotEmpty() && player1Name != "TTTBot" && player2Name != "TTTBot" && player1Name != player2Name && player2Name != player1Name) {

                val player1 = players?.find { player -> player.name == player1Name } ?: run {
                    val player = Player(player1Name, 0)
                    viewModel.insert(player)
                    player
                }

                val player2 = if (gameMode == "Versus") {
                    players?.find { player -> player.name == player2Name } ?: run {
                        val player = Player(player2Name, 0)
                        viewModel.insert(player)
                        player
                    }
                } else null

                val bot = players?.find { player -> player.name == "TTTBot" } ?: run {
                    val player = Player("TTTBot", 0)
                    viewModel.insert(player)
                    player
                }

                findNavController().navigate(R.id.action_startFragment_to_gameFragment, Bundle().also { bundle ->
                    bundle.putString("playMode", gameMode)
                    bundle.putString("difficulty", difficulty)
                    bundle.putParcelable("player", player1)
                    if(player2 != null) bundle.putParcelable("player2", player2)
                    if(gameMode == "Ai") bundle.putParcelable("player2", bot)
                })
            } else {
                if (player1Name.isEmpty()) {
                    Toast.makeText(context, "Please fill in required fields!", Toast.LENGTH_SHORT).show()
                } else if(player1Name == player2Name || player2Name == player1Name) {
                    Toast.makeText(context, "Names cannot be the same", Toast.LENGTH_SHORT).show()
                } else if(player1Name == "TTTBot" || player2Name == "TTTBot") {
                    Toast.makeText(context, "TTTBot is a reserved name", Toast.LENGTH_SHORT).show()
                }
            }
            
        }
    }
}
