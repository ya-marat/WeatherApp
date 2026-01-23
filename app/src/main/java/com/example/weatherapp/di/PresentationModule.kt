package com.example.weatherapp.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.weatherapp.presentation.details.DetailsStoreFactory
import dagger.Module
import dagger.Provides

@Module
interface PresentationModule {

    companion object {

        @Provides
        fun provideStoreFactory(): StoreFactory = DefaultStoreFactory()
    }

}