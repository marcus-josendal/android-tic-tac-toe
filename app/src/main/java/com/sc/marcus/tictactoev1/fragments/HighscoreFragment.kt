package com.sc.marcus.tictactoev1.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sc.marcus.tictactoev1.R
import com.sc.marcus.tictactoev1.database.GameViewModel
import kotlinx.android.synthetic.main.fragment_highscore_list.*

class HighscoreFragment : Fragment() {

    private var columnCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_highscore_list, container, false)
        val viewModel = activity?.run {
            ViewModelProviders.of(this).get(GameViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        val recyclerView = view.findViewById<RecyclerView>(R.id.list)

        if (recyclerView is RecyclerView) {
            with(recyclerView) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = HighscoreRecyclerViewAdapter(
                    viewModel.allPlayersAndScore.value ?: emptyList()
                )
            }
        }
        viewModel.allPlayersAndScore.observe(this, Observer { players ->
            if (recyclerView is RecyclerView && players != null) {
                val adapter = recyclerView.adapter
                if (adapter is HighscoreRecyclerViewAdapter) {
                    adapter.setData(players)
                }
            }
        })
        return view
    }
}
