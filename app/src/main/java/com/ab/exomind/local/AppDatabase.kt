package com.ab.exomind.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ab.exomind.model.Album
import com.ab.exomind.model.User

/**
 * Created by Aya Boussaadia on 19,February,2021
 */

@Database(entities = [User::class, Album::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): UserDAO
}