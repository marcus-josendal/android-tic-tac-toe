package com.sc.marcus.tictactoev1.database

import android.app.Application
import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import android.os.AsyncTask


class PlayerRepository(private val application: Application) {
    val playerDaoRen = AppDatabase.getDatabase(application)

    @WorkerThread
    fun insert(player: Player) {
        GetUsersAsyncTask(playerDaoRen.playerDao()).execute(player)
    }

    fun getAll():LiveData<List<Player>> {
        return playerDaoRen.playerDao().getAll()
    }


    fun getAllSync() = GetActuallyUsersAsyncTask(playerDaoRen.playerDao()).execute()

    @WorkerThread
    fun updateScoreByName(score: Int, name: String) {
        UpdateUsersAsyncTask(playerDaoRen.playerDao()).execute(Player(name, score))
    }

    private inner class GetActuallyUsersAsyncTask internal constructor(private val playerDao: PlayerDao): AsyncTask<Void, Void, List<Player>>() {
        override fun doInBackground(vararg params: Void?): List<Player> {
            return playerDao.getAllSync()
        }
    }

    private inner class GetUsersAsyncTask internal constructor(private val playerDao: PlayerDao): AsyncTask<Player, Void, Void>() {
        override fun doInBackground(vararg params: Player): Void? {
            playerDao.insert(params[0])
            return null
        }
    }

    private inner class UpdateUsersAsyncTask internal constructor(private val playerDao: PlayerDao): AsyncTask<Player, Void, Void>() {
        override fun doInBackground(vararg params: Player): Void? {
            playerDao.updateScoreByName(params[0].score, params[0].name)
            return null
        }
    }

}
