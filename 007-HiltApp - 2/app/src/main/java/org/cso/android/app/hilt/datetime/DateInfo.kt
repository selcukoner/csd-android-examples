package org.cso.android.app.hilt.datetime

import org.cso.android.util.datetime.di.module.annotation.SystemLocalDateInterceptor
import org.cso.android.util.datetime.di.module.formatter.annotation.LocalDateFormatterInterceptor
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateInfo @Inject constructor() {
    @Inject
    @SystemLocalDateInterceptor
    lateinit var date: LocalDate

    @Inject
    @LocalDateFormatterInterceptor
    lateinit var formatter: DateTimeFormatter

    override fun toString() = formatter.format(date)
}