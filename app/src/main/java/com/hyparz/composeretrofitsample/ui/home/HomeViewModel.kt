package com.hyparz.composeretrofitsample.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyparz.composeretrofitsample.core.domain.repository.HomeRepository
import com.hyparz.composeretrofitsample.core.network.NetworkResponse
import com.hyparz.composeretrofitsample.data.model.User
import com.hyparz.composeretrofitsample.data.model.UsersListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    val homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)

    init {
        viewModelScope.launch(Dispatchers.IO) {

            repository.getUserList().collect { result ->
                withContext(Dispatchers.Main) {
                    homeUiState.value = when (result) {
                        is NetworkResponse.Success -> {
                            if (result.data?.results?.isEmpty() == true)
                            {
                                HomeUiState.Empty
                            }
                            else{
                                result.data?.results?.let { HomeUiState.Success(it) }?:HomeUiState.Empty
                            }
                        }
                        is NetworkResponse.Error ->{
                            HomeUiState.Error(message = result.message)
                        }
                    }
                }
            }
        }
    }

}


sealed interface HomeUiState {
    data class Success(val users: List<User>) : HomeUiState
    data class Error(val message:String?) : HomeUiState
    object Empty : HomeUiState
    object Loading : HomeUiState
}