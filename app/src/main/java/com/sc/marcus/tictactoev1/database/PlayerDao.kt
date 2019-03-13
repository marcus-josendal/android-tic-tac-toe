package com.sc.marcus.tictactoev1.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PlayerDao {

    @Query("SELECT * FROM player_table")
    fun getAll(): LiveData<List<Player>>


    @Query("SELECT * FROM player_table")
    fun getAllSync(): List<Player>

    /*
    @Query("SELECT score FROM player_table WHERE name LIKE :name")
    fun findScoreByName(name: String) */

    @Query("UPDATE player_table SET score = :score WHERE name = :name")
    fun updateScoreByName(score: Int, name: String)

    @Query("SELECT * FROM player_table ORDER BY score ASC")
    fun getAllByAscendingScore(): LiveData<List<Player>>

    @Query("SELECT score FROM player_table WHERE name = :name")
    fun getScoreByName(name: String): LiveData<Int>

    @Insert
    fun insert(player: Player)

    @Delete
    fun delete(player: Player)
}