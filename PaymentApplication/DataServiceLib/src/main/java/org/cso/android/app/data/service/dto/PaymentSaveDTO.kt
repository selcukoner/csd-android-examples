package org.cso.android.app.data.service.dto

import java.io.Serializable

data class PaymentSaveDTO(var userName: String,
                          var price: Double, var quantity: Double, var desc:String): Serializable