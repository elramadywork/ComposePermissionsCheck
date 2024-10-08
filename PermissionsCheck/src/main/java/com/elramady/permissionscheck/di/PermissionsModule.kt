package com.elramady.permissionscheck.di

import android.content.Context
import com.elramady.permissionscheck.data.permissions.PermissionsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PermissionsModule {
    @Provides
    @Singleton
    fun providePermissionsManager(@ApplicationContext context: Context): PermissionsManager {
        return PermissionsManager(context)
    }
}