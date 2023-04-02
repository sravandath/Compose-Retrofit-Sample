package com.hyparz.composeretrofitsample.ui.userdetails.navigation

import android.net.Uri
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hyparz.composeretrofitsample.core.decoder.StringDecoder
import com.hyparz.composeretrofitsample.data.model.User
import com.hyparz.composeretrofitsample.ui.userdetails.UserDetailsRoute
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/*

fun NavController.navigateToUserDetails(user: User) {
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    val jsonAdapter = moshi.adapter(User::class.java).lenient()
    val userJson = jsonAdapter.toJson(user)
    val encodedId = Uri.encode(userJson)

    this.navigate("user-details/$encodedId")
}

fun NavGraphBuilder.userDetailsScreen(
    onBackClick: () -> Unit
) {
    composable("user-details/{item}",
        arguments = listOf(navArgument("item") {
            type = NavType.StringType
        })
    ) {
        it.arguments?.getString("item")?.let { userJson ->
          *//*  val user = jsonString.fromJson(User::class.java)
            DetailScreen( navController = navController, user = user )

            val userJson = it.arguments?.getString("user")*//*
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val jsonAdapter = moshi.adapter(User::class.java).lenient()
            userJson.let { json ->
                jsonAdapter.fromJson(json)
                    ?.let { user -> UserDetailsRoute(onBackClick = onBackClick, user) }
            }
        }
    }
}

 */




@VisibleForTesting
internal const val userIdArg = "userId"

internal class UserArgs(val userId: String) {
    constructor(savedStateHandle: SavedStateHandle, stringDecoder: StringDecoder) :
            this(stringDecoder.decodeString(checkNotNull(savedStateHandle[userIdArg])))
}

fun NavController.navigateToUserDetails(user: User) {
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    val jsonAdapter = moshi.adapter(User::class.java).lenient()
    val userJson = jsonAdapter.toJson(user)
    val encodedId = Uri.encode(userJson)
    this.navigate("user_route/$encodedId")
}

fun NavGraphBuilder.userDetailsScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "user_route/{$userIdArg}",
        arguments = listOf(
            navArgument(userIdArg) { type = NavType.StringType }
        )
    ) {
        it.arguments?.getString(userIdArg)?.let { userJson ->
            UserDetailsRoute(onBackClick = onBackClick)
        }
    }
}
