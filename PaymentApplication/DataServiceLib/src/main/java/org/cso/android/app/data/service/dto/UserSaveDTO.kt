package org.cso.android.app.data.service.dto

import java.io.Serializable
import java.time.LocalDate

data class UserSaveDTO(val userName: String, val password: String,
                       val firstName:String, val lastName:String,
                       val birthDate: LocalDate, val middleName:String? = null): Serializable{

}