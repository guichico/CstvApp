package com.codechallenge.common.di

import android.content.Context
import com.codechallenge.common.string.AppStringProvider
import com.codechallenge.common.string.StringProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    @Singleton
    fun providesStringProvider(@ApplicationContext context: Context): StringProvider = AppStringProvider(context)
}