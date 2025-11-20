package ru.otus.arch.domain.repository

import ru.otus.arch.domain.model.Weather

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): Weather
}
