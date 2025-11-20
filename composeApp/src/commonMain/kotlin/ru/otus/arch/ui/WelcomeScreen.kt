package ru.otus.arch.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import arch.composeapp.generated.resources.Res
import arch.composeapp.generated.resources.next
import arch.composeapp.generated.resources.welcome
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.otus.arch.data.AppGesture

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(onGesture: (AppGesture) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather App") },
            )
        }
    ) { paddingValues ->
        WeatherScreen()
    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    MaterialTheme {
        WelcomeScreen { }
    }
}