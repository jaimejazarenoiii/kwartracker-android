package com.kwartracker.android.utils.di

import com.kwartracker.android.login.repository.LoginRepository
import com.kwartracker.android.login.repository.LoginRepositoryImpl
import com.kwartracker.android.signup.repository.SignupRepository
import com.kwartracker.android.signup.repository.SignupRepositoryImpl
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

    @Binds
    @ViewModelScoped
    abstract fun bindSignupRepository(repo: SignupRepositoryImpl): SignupRepository
}
