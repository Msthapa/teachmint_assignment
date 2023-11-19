package com.teachmint.tmassignment.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RepositoryDAO {

    @Insert
    suspend fun insertRepositoryItem( repository : RepositoryEntity)

    @Query("SELECT * FROM repository_table")
    suspend fun getAllRepositories(): List<RepositoryEntity?>?
}