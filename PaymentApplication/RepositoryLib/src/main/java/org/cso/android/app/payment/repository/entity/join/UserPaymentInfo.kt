package org.cso.android.app.payment.repository.entity.join

import org.cso.android.app.payment.repository.entity.User

data class UserPaymentInfo(var username:User,var description:Double,
                           var quantity:Double, var unitPrice:Double){}
