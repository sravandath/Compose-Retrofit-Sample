package com.hyparz.composeretrofitsample.ui.userdetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hyparz.composeretrofitsample.core.decoder.StringDecoder
import com.hyparz.composeretrofitsample.data.model.User
import com.hyparz.composeretrofitsample.ui.userdetails.navigation.UserArgs
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    stringDecoder: StringDecoder
) : ViewModel() {

    private val presetArgs: UserArgs = UserArgs(savedStateHandle, stringDecoder)

    val userState: MutableStateFlow<User?> = MutableStateFlow(null)


    init {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter = moshi.adapter(User::class.java).lenient()
        val user = jsonAdapter.fromJson(presetArgs.userId)
        userState.value = user
    }

}

