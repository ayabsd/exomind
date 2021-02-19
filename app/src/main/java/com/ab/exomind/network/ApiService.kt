package com.ab.exomind.network

import com.ab.exomind.model.User
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Aya Boussaadia on 19,February,2021
 */

interface ApiService {
    /**
     * Get the list of the pots from the API
     */
    @GET("/users")
    fun getUsers(): Observable<List<User>>
}