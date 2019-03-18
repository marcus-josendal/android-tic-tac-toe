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

class HighscoreFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_highscore_list, container, false)
        val viewModel = activity?.run {
            ViewModelProviders.of(this).get(GameViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
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
            if (view is RecyclerView && players != null) {
                val adapter = view.adapter
                if (adapter is HighscoreRecyclerViewAdapter) {
                    adapter.setData(players)
                }
            }
        })
        return view
    }


    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            HighscoreFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
