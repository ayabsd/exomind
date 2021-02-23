package com.ab.exomind

import com.ab.exomind.network.ApiService
import com.ab.exomind.utils.BASE_URL
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Aya Boussaadia on 23,February,2021
 */
class ApiServiceTest {

    private lateinit var service: ApiService

    @Before
    internal fun setUp() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        service = retrofit.create(ApiService::class.java)
    }

    @Test
    internal fun should_callServiceWithRx() {
        service.getUsers().subscribe { repos ->
            repos.forEach(::println)
        }
        service.getAlbums("1").subscribe { repos ->
            repos.forEach(::println)
        }
        service.getPhotos(11).subscribe { repos ->
            repos.forEach(::println)
        }
    }


}