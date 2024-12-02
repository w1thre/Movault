package com.codewithre.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codewithre.core.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}