package com.example.memefon.di

import android.content.Context
import com.example.memefon.ui.App
import com.example.memefon.di.qualifiers.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context) : App {
        return app as App
    }

    @Provides
    @Singleton
    @IoDispatcher
    fun provideIOCoroutineDispatcher(): CoroutineDispatcher {
        return IO
    }
}
