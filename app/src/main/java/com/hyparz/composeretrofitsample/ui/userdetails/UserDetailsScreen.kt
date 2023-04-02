package com.hyparz.composeretrofitsample.ui.userdetails

import android.content.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hyparz.composeretrofitsample.R
import com.hyparz.composeretrofitsample.core.designsystem.theme.Black20
import com.hyparz.composeretrofitsample.data.model.User


@Composable
internal fun UserDetailsRoute(
    onBackClick: () -> Unit,
    viewModel: UserDetailsViewModel = hiltViewModel()
) {
    val user: User? by viewModel.userState.collectAsState()

    user?.let {
        UserDetailsScreen(
            user=it,
            onBackClick = onBackClick,
        )
    }
}

@Composable
fun UserDetailsScreen(
    user: User,
    onBackClick: () -> Unit
) {
    HeaderImage(user = user)
    BackButton(onBackClick)
}

@Composable
private fun HeaderImage(
    user: User
) {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Column(modifier = Modifier.width(screenWidth)) {
        AsyncImage(
            model = user.picture.large,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight.times(.6f)),
            contentScale = ContentScale.Crop
        )
        Row(modifier = Modifier.padding(16.dp)) {
            Column() {
                Text(
                    text = "${user.name.title} ${user.name.first} ${user.name.last}",
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text ="${user.location.street}, ${user.location.city}, ${user.location.state}, ${user.location.country}",
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    modifier = Modifier,
                    text = user.email,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    modifier = Modifier,
                    text = user.phone,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun BackButton(onBackClick: () -> Unit){
    Box(
        modifier = Modifier
            .padding(top = 12.dp, start = 12.dp)
            .statusBarsPadding()
            .background(Black20, shape = RoundedCornerShape(12.dp))
    ) {
        Icon(
            modifier = Modifier
                .clickable(onClick = { onBackClick() })
                .size(48.dp)
                .padding(8.dp),
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
            contentDescription = "",
            tint = Color.White
        )
    }
}
