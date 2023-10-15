package org.cso.android.util.datetime.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import org.cso.android.util.datetime.di.module.annotation.SystemLocalTimeInterceptor
import java.time.LocalTime

@Module
@InstallIn(ActivityComponent::class)
object SystemLocalTimeModule {
    @Provides
    @SystemLocalTimeInterceptor
    fun provideLocalTime() = LocalTime.now()

}
