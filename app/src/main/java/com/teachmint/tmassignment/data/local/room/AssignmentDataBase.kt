package com.teachmint.tmassignment.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RepositoryEntity::class], version = 1)
abstract class AssignmentDataBase : RoomDatabase() {
    abstract fun repositoryDAO() : RepositoryDAO
 }