package org.cso.android.app.hilt.datetime

import org.cso.android.util.datetime.di.module.annotation.SystemLocalDateTimeInterceptor
import org.cso.android.util.datetime.di.module.formatter.annotation.LocalDateTimeFormatterInterceptor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class DateTimeInfo @Inject constructor(@LocalDateTimeFormatterInterceptor var formatter: DateTimeFormatter,
                                       @SystemLocalDateTimeInterceptor var dateTime: LocalDateTime) {

    override fun toString() = formatter.format(dateTime)
}