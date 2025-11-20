package ru.otus.arch.domain.usecase

import ru.otus.arch.domain.model.Weather

interface GetWeatherUseCase {
    suspend fun execute(city: String): Weather
}
