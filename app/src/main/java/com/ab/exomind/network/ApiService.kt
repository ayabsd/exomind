package com.ab.exomind.network

import com.ab.exomind.model.Album
import com.ab.exomind.model.Photo
import com.ab.exomind.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Aya Boussaadia on 19,February,2021
 */

interface ApiService {

    @GET("/users")
    fun getUsers(): Observable<List<User>>

    @GET("users/{userId}/albums?userId=1")
    fun getAlbums(@Path("userId") userId: String): Observable<List<Album>>

    @GET("users/1/photos")
    fun getPhotos(@Query("albumId") albumId: Int): Observable<List<Photo>>



}