package ru.otus.arch.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.otus.arch.memory.DependencyFactory
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope

@Composable
fun WeatherScreen() {
    val getWeatherUseCase = remember { DependencyFactory.createGetWeatherUseCase() }
    var weather by remember { mutableStateOf<ru.otus.arch.domain.model.Weather?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var city by remember { mutableStateOf("London") }

    // Используем coroutineScope для обработки асинхронных операций
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        isLoading = true
        weather = getWeatherUseCase.execute(city)
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("City") }
        )
        Button(
            onClick = {
                isLoading = true
                coroutineScope.launch {
                    weather = getWeatherUseCase.execute(city)
                    isLoading = false
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Get Weather")
        }

        if (isLoading) {
            CircularProgressIndicator()
        }

        weather?.let { w ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("City: ${w.city}", style = MaterialTheme.typography.headlineMedium)
                Text("Temperature: ${w.temperature}°C", style = MaterialTheme.typography.bodyLarge)
                Text("Description: ${w.description}", style = MaterialTheme.typography.bodyLarge)
                Text("Humidity: ${w.humidity}%", style = MaterialTheme.typography.bodyLarge)
                Text("Wind Speed: ${w.windSpeed} m/s", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}