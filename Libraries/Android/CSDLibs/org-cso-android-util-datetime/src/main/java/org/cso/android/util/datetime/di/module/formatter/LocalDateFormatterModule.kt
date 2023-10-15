package org.cso.android.util.datetime.di.module.formatter

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.cso.android.util.datetime.di.module.formatter.annotation.LocalDateFormatterInterceptor
import java.time.format.DateTimeFormatter
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalDateFormatterModule {
    @Provides
    @Singleton
    @LocalDateFormatterInterceptor
    fun provideFormatter() : DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
}