package org.cso.android.util.datetime.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import org.cso.android.util.datetime.di.module.annotation.SystemLocalDateInterceptor
import java.time.LocalDate

@Module
@InstallIn(ActivityComponent::class)
object SystemLocalDateModule {
    @Provides
    @SystemLocalDateInterceptor
    fun provideLocalDate() = LocalDate.now()


}
