package ru.otus.arch.memory.repository

import ru.otus.arch.domain.model.Weather
import ru.otus.arch.domain.repository.WeatherRepository

class WeatherRepositoryImpl : WeatherRepository {
    override suspend fun getCurrentWeather(city: String): Weather {

        return Weather(
            city = city,
            temperature = 20.0,
            description = "Sunny",
            humidity = 50,
            windSpeed = 5.0
        )
    }
}