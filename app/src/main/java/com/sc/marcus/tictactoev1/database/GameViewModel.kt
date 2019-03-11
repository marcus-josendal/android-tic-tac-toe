package com.sc.marcus.tictactoev1.database

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: PlayerRepository
    val allPlayersAndScore: LiveData<List<Player>>

    init {
        val playerDao = AppDatabase.getDatabase(application).playerDao()
        repository = PlayerRepository(playerDao)
        allPlayersAndScore = repository.allPlayersAndScore
    }

    fun insert(player: Player) = scope.launch(Dispatchers.IO) {
        repository.insert(player)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}