package org.cso.android.app.multipleactivity.viewmodel

import org.cso.android.app.multipleactivity.PaymentActivity

data class PaymentInfo(var name:String ="",
                       var unitPrice:Double=0.0,
                       var quantity: Int=0) {


    override fun toString() ="$name, $unitPrice * $quantity = ${unitPrice* quantity}"

}