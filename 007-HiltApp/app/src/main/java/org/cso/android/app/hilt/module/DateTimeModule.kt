package org.cso.android.app.hilt.module

import android.content.Context
import android.widget.Toast
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDateTime

@Module
@InstallIn(ActivityComponent::class)
object DateTimeModule {
    @Provides
    fun provideDateTime(@ApplicationContext context: Context): LocalDateTime
    {
        Toast.makeText(context, "provideDateTime", Toast.LENGTH_SHORT).show()

        return LocalDateTime.now()
    }

}
