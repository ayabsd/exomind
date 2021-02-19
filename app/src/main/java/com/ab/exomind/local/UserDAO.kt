package com.ab.exomind.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ab.exomind.model.User

/**
 * Created by Aya Boussaadia on 19,February,2021
 */

@Dao
interface UserDAO {
    @get:Query("SELECT * FROM users")
    val all: List<User>

    @Insert
    fun insertAll(vararg photo: User)
}