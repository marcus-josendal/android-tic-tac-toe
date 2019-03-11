package com.sc.marcus.tictactoev1.database

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class PlayerRepository(private val playerDao: PlayerDao) {

    val allPlayersAndScore: LiveData<List<Player>> = playerDao.getAllByAscendingScore()

    @WorkerThread
    fun insert(player: Player) {
        playerDao.insert(player)
    }

    @WorkerThread
    fun delete(player: Player) {
        playerDao.delete(player)
    }

    @WorkerThread
    fun updateScoreByName(score: Int, name: String) {
        playerDao.updateScoreByName(score, name)
    }

    /*
    @WorkerThread
    fun findScoreByName(name: String) {
        playerDao.findScoreByName(name)
    } */

    @WorkerThread
    fun getAll() {
        playerDao.getAll()
    }

}
