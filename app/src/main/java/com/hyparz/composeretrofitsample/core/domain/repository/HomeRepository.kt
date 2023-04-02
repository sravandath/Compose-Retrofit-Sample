package com.hyparz.composeretrofitsample.core.domain.repository

import com.hyparz.composeretrofitsample.core.network.NetworkResponse
import com.hyparz.composeretrofitsample.data.model.UsersListResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getUserList(): Flow<NetworkResponse<UsersListResponse>>

}