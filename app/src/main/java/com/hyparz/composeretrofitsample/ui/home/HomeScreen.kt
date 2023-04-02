package com.hyparz.composeretrofitsample.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hyparz.composeretrofitsample.core.designsystem.theme.Black40
import com.hyparz.composeretrofitsample.data.model.User

@Composable
fun HomeRoute(
    navigateToUserDetails: (User) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {


        val homeUiState = viewModel.homeUiState.collectAsState()

        when (homeUiState.value) {
            is HomeUiState.Empty -> {
                Message(
                    modifier = Modifier.fillMaxSize(),
                    message = stringResource(id = com.hyparz.composeretrofitsample.R.string.no_item)
                )
            }
            is HomeUiState.Error -> Message(
                modifier = Modifier.fillMaxSize(),
                message = (homeUiState.value as HomeUiState.Error).message
            )
            HomeUiState.Loading -> BottomProgressIndicator(modifier = Modifier.fillMaxSize())
            is HomeUiState.Success -> {
                val userList = (homeUiState.value as HomeUiState.Success).users
                val state = rememberLazyGridState()
                LazyVerticalGrid(
                    state = state,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item(span = { GridItemSpan(2) }) { Header() }
                    items(userList.size) { item ->
                        UserCardItem(user =userList[item] , navigateToUserDetails = navigateToUserDetails)
                    }
                }
            }
        }


    }

}


@Composable
private fun Header() {
    Column() {
        Text(
            modifier = Modifier
                .padding(top = 12.dp)
                .statusBarsPadding(),
            text = stringResource(id = com.hyparz.composeretrofitsample.R.string.app_name),
            style = MaterialTheme.typography.titleLarge, color = Color.White
        )
    }
}

@Composable
fun BottomProgressIndicator(modifier: Modifier, progressSize: Dp = 50.dp) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(progressSize))
    }
}

@Composable
fun Message(modifier: Modifier, message: String?) {
    message?.let {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            Text(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .statusBarsPadding(),
                text = it,
                style = MaterialTheme.typography.titleLarge,color = Color.White
            )
        }
    }
}


@Composable
fun UserCardItem(
    user: User,
    navigateToUserDetails: (User) -> Unit
) {
    Card(
        modifier = Modifier
            .height(220.dp)
            .clickable {
                navigateToUserDetails(user)
            },
        backgroundColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(12.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AsyncImage(
                model = user.picture.large,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Black40)
                    .align(Alignment.BottomCenter), verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material3.Text(
                    text = "${user.name.title} ${user.name.first} ${user.name.last}",
                    modifier = Modifier
                        .padding(6.dp)
                        .weight(1f), color = Color.White, fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }

}
