package com.sc.marcus.tictactoev1


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment() {

    var difficulty: String? = null
    var gameMode: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnVs.setOnClickListener {
            gameMode = "Versus"
        }

        btnAi.setOnClickListener {
            btnAi.setBackgroundColor(Color.BLUE)
            gameMode = "Ai"
        }

        btnEasy.setOnClickListener {
            btnEasy.setBackgroundColor(Color.BLUE)
            difficulty = "Easy"
        }

        btnMedium.setOnClickListener {
            difficulty = "Medium"
            btnMedium.setBackgroundColor(Color.BLUE)
        }

        btnStartGame.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_gameFragment, Bundle().also { bundle ->
                bundle.putString("playMode", gameMode)
                bundle.putString("difficulty", difficulty)
            })
        }
    }
}
