package com.sc.marcus.tictactoev1.fragments

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sc.marcus.tictactoev1.PlayerListAdapter
import com.sc.marcus.tictactoev1.R
import com.sc.marcus.tictactoev1.database.GameViewModel
import com.sc.marcus.tictactoev1.database.Player

class HighscoreFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_highscore, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = PlayerListAdapter(activity!!.applicationContext)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }

    companion object {
        const val newPlayerActivityRequestCode = 1
    }

}
