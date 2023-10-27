package org.cso.android.app.payment.repository.entity

import java.io.Serializable
import java.time.LocalDate

data class User(var userName: String = "", var password: String="",
                var firstName:String ="", var middleName:String? ="", var lastName:String="",
                var birthDate:LocalDate = LocalDate.now(),
                var registerDate: LocalDate= LocalDate.now()): Serializable {
    constructor(
        userName: String, password: String,
        firstName: String, lastName: String,
        birthDate: LocalDate, registerDate: LocalDate
    )
            : this(userName, password, firstName, null, lastName, birthDate, registerDate)
}