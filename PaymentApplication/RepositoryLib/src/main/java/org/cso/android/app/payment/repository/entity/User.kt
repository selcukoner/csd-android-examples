package org.cso.android.app.payment.repository.entity

import java.time.LocalDate

data class User(var userName: String, var password: String,
                var firstName:String, var middleName:String?, var lastName:String,
                var birthDate:LocalDate, var registerDate: LocalDate)