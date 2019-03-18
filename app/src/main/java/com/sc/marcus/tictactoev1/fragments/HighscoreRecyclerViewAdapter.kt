package com.sc.marcus.tictactoev1.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sc.marcus.tictactoev1.R
import com.sc.marcus.tictactoev1.database.Player

import kotlinx.android.synthetic.main.fragment_highscore_player.view.*

class HighscoreRecyclerViewAdapter(
    private var mValues: List<Player>
) : RecyclerView.Adapter<HighscoreRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_highscore_player, parent, false)
        return ViewHolder(view)
    }

    val values : List<Player> get() {
        return mValues.sortedByDescending { it.score }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.mIdView.text = item.name
        holder.mContentView.text = item.score.toString()

        with(holder.mView) {
            tag = item
        }
    }

    fun setData(players: List<Player>) {
        this.mValues = players
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
