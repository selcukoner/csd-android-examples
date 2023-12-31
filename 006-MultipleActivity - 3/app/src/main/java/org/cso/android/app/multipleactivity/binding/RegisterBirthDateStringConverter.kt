package org.cso.android.app.multipleactivity.binding

import androidx.databinding.InverseMethod
import java.time.LocalDate
import java.time.LocalDateTime

import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object RegisterBirthDateStringConverter {
    private val mformatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    private var mFail = false

    val fail: Boolean
        get() = mFail

    var failStr = ""

    @InverseMethod("toStr")
    @JvmStatic
    fun toLocalDate(str: String):LocalDate
    {
        var result =  LocalDate.of(1990,1,1)
        try {
            mFail = false
            result = LocalDate.parse(str, mformatter)
        }catch (ignore: DateTimeParseException){
            mFail = true

        }

        return  result
    }

    @JvmStatic
    fun toStr(birtdate: LocalDate) = mformatter.format(birtdate)

}