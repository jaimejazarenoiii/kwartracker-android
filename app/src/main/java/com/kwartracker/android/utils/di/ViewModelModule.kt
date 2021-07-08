package com.kwartracker.android.utils.di

import com.kwartracker.android.login.repository.LoginRepository
import com.kwartracker.android.login.repository.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRepository(repo: LoginRepositoryImpl): LoginRepository
}
