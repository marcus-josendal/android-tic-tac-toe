package com.sc.marcus.tictactoev1.database

import android.arch.persistence.room.*

@Entity(tableName = "player_table")
data class Player(

    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "score")
    var score: Int?


)
