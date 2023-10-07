package org.cso.android.app.multipleactivity.viewmodel

import org.cso.android.app.multipleactivity.PaymentActivity

data class PaymentInfo(var productName:String ="",
                       var productPrice:Double=0.0,
                       var productAmount: Int=0) {
    override fun toString() ="$productName, $productPrice * $productAmount = ${productPrice* productAmount}"

}