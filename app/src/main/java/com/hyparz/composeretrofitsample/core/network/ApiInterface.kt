package com.hyparz.composeretrofitsample.core.network

import com.hyparz.composeretrofitsample.data.model.UsersListResponse
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiInterface {

    @GET("/api?results=100")
    suspend fun getUserList(): UsersListResponse
}