package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.repository.SearchRepository
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject


class GetCurrentWeatherUseCase @Inject constructor(
    val repository: WeatherRepository
) {
    suspend operator fun invoke(cityId: Int) = repository.getWeather(cityId)
}