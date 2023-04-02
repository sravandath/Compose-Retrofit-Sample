package com.hyparz.composeretrofitsample.data.repository

import com.hyparz.composeretrofitsample.core.domain.repository.HomeRepository
import com.hyparz.composeretrofitsample.core.network.ApiInterface
import com.hyparz.composeretrofitsample.core.network.AppDispatchers
import com.hyparz.composeretrofitsample.core.network.Dispatcher
import com.hyparz.composeretrofitsample.core.network.NetworkResponse
import com.hyparz.composeretrofitsample.data.model.UsersListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val apiInterface: ApiInterface
) :
    HomeRepository {
    override suspend fun getUserList(): Flow<NetworkResponse<UsersListResponse>> = flow {
        emit(
            try {
                NetworkResponse.Success(apiInterface.getUserList())
            } catch (e: Exception) {
                NetworkResponse.Error("An unknown error occured: ${e.localizedMessage}")
            }
        )
    }.flowOn(ioDispatcher)
}