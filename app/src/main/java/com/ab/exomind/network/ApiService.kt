package com.ab.exomind.network

import com.ab.exomind.model.Album
import com.ab.exomind.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Aya Boussaadia on 19,February,2021
 */

interface ApiService {

    @GET("/users")
    fun getUsers(): Observable<List<User>>

    @GET("users/{userId}/albums?userId=1")
    fun getAlbums(@Path("userId") userId: String): Observable<List<Album>>


}