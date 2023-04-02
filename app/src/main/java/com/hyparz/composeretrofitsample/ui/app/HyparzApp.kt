package com.hyparz.composeretrofitsample.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.hyparz.composeretrofitsample.core.designsystem.component.AppBackground
import com.hyparz.composeretrofitsample.core.navigation.AppNavHost
import com.hyparz.composeretrofitsample.ui.app.HyparzAppState
import com.hyparz.composeretrofitsample.ui.app.rememberHyparzAppState

@OptIn(
    ExperimentalLayoutApi::class,
    ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class,
)
@Composable
fun HyparzApp(
    windowSizeClass: WindowSizeClass,
    appState: HyparzAppState = rememberHyparzAppState(
        windowSizeClass = windowSizeClass
    ),
) {
    val background: @Composable (@Composable () -> Unit) -> Unit =
        { content ->
            AppBackground(content = content)
        }

    background {

        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            modifier = Modifier.semantics {
                testTagsAsResourceId = true
            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) { padding ->


            Row(
                Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal
                        )
                    )
            ) {

                AppNavHost(
                    navController = appState.navController,
                    onBackClick = appState::onBackClick,

                    modifier = Modifier
                        .padding(padding)
                        .consumedWindowInsets(padding)
                )

            }
        }
    }
}
