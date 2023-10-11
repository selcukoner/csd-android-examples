package org.cso.android.app.multipleactivity.viewmodel

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class RegisterInfo(var firstName:String = "",
                        var secondName:String?= "",
                        var lastName:String = "",
                        var email:String = "",
                        var password:String = "",
                        var birthDate:LocalDate = LocalDate.now()){

    val age:Double
        get() = ChronoUnit.DAYS.between(birthDate, LocalDate.now()) / 365.0

    override fun toString(): String  = "$firstName $secondName $lastName $email $password $birthDate"

    val fullName:String
        get() {
            var middleStr = if (secondName != null) "$secondName " else ""
            return "${"$firstName "}$middleStr$lastName"
        }
}