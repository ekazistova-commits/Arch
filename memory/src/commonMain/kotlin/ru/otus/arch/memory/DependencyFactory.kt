
package ru.otus.arch.memory

import ru.otus.arch.domain.repository.WeatherRepository
import ru.otus.arch.domain.usecase.GetWeatherUseCase
import ru.otus.arch.memory.repository.WeatherRepositoryImpl
import ru.otus.arch.memory.usecase.GetWeatherUseCaseImpl

object DependencyFactory {
    fun createWeatherRepository(): WeatherRepository = WeatherRepositoryImpl()

    fun createGetWeatherUseCase(): GetWeatherUseCase {
        val repository = createWeatherRepository()
        return GetWeatherUseCaseImpl(repository)
    }
}