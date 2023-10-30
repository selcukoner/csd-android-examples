package org.cso.android.app.data.service.dto

import java.io.Serializable
import java.time.LocalDateTime

data class LoginInfoStatusDTO(var username:String ="", var password:String = "",
                              var success: Boolean = true,
                              var loginDateTimeStr: String) :Serializable
{
    override fun toString(): String  = "Login Date Time:$loginDateTimeStr, Status: ${if (success) "Success " else "fail"}"
}