package com.sc.marcus.tictactoev1.database

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class GameViewModel(application: Application) : AndroidViewModel(application) {


    private val repository: PlayerRepository = PlayerRepository(application)
    var allPlayersAndScore: LiveData<List<Player>>

    init {
        allPlayersAndScore = repository.getAll()
    }

    val allPlayersAndScoreSync get() = repository.getAllSync()

    fun insert(player: Player) {
        repository.insert(player)
    }

    fun updateScoreByName(score: Int, name: String) {
        repository.updateScoreByName(score, name)
    }

}