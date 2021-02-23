package com.ab.exomind.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ab.exomind.model.Album
import com.ab.exomind.model.Photo
import com.ab.exomind.model.User

/**
 * Created by Aya Boussaadia on 19,February,2021
 */

@Dao
interface UserDAO {
    @Insert
    fun insertAllUsers(vararg user: User)

    @Insert
    fun insertAllAlbums(vararg album: Album)

    @Insert
    fun insertAllPhotos(vararg photo: Photo)

    @Query("SELECT * FROM album WHERE userId = :userId")
    fun getUserAlbums(userId: String): List<Album>


    @Query("SELECT * FROM photo WHERE albumId = :idAlbum")
    fun getAlbumPhoto(idAlbum: Int): List<Photo>

    @get:Query("SELECT * FROM users")
    val allUsers: List<User>

}