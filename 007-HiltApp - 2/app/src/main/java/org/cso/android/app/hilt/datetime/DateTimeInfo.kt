package org.cso.android.app.hilt.datetime

import android.content.Context
import android.widget.Toast
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import org.csystem.android.util.datetime.di.module.annotation.SystemLocalDateTimeInterceptor
import org.csystem.android.util.datetime.di.module.formatter.annotation.LocalDateTimeFormatterInterceptor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class DateTimeInfo @Inject constructor(
    @LocalDateTimeFormatterInterceptor var formatter: DateTimeFormatter,
    @SystemLocalDateTimeInterceptor var dateTime: LocalDateTime,
    @ActivityContext context: Context) {

    init {
        Toast.makeText(context, "DateTimeInfo created -> ${this}",Toast.LENGTH_LONG).show()
    }
    override fun toString() = formatter.format(dateTime)
}