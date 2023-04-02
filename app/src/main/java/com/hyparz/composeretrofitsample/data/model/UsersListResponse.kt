package com.hyparz.composeretrofitsample.data.model

import com.google.gson.annotations.SerializedName

data class UsersListResponse(
    @SerializedName("results" ) var results : List<User> = arrayListOf(),
    @SerializedName("info"    ) var info    : Info
)