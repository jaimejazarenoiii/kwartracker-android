package com.kwartracker.android.utils.di

import com.kwartracker.android.utils.network.KwartrackerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApolloModule {

    @Singleton
    @Provides
    fun provideApolloClient() = KwartrackerApi()
}
