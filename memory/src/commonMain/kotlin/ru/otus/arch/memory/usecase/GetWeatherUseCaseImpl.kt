package ru.otus.arch.memory.usecase

import ru.otus.arch.domain.model.Weather
import ru.otus.arch.domain.repository.WeatherRepository
import ru.otus.arch.domain.usecase.GetWeatherUseCase

class GetWeatherUseCaseImpl(
    private val weatherRepository: WeatherRepository
) : GetWeatherUseCase {
    override suspend fun execute(city: String): Weather {
        return weatherRepository.getCurrentWeather(city)
    }
}