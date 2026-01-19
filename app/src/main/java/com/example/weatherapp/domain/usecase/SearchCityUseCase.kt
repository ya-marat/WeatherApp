package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.repository.SearchRepository
import javax.inject.Inject


class SearchCityUseCase @Inject constructor(
    val repository: SearchRepository
) {
    suspend operator fun invoke(query: String) = repository.search(query)
}