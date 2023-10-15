package org.cso.android.util.datetime.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import org.cso.android.util.datetime.di.module.annotation.SystemLocalDateTimeInterceptor
import java.time.LocalDateTime

@Module
@InstallIn(ActivityComponent::class)
object SystemLocalDateTimeModule {
    @Provides
    @SystemLocalDateTimeInterceptor
    fun provideDateTime()  = LocalDateTime.now()


}
