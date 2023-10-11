package org.cso.android.app.multipleactivity.viewmodel

import org.cso.android.app.multipleactivity.PaymentActivity
import java.io.Serializable
import java.nio.DoubleBuffer

data class PaymentInfo(var name:String ="",
                       var unitPrice:Double=0.0,
                       var quantity: Int=0) :Serializable{

    val total: Double
        get() = unitPrice * quantity

    override fun toString() ="$name, $unitPrice * $quantity = ${unitPrice* quantity}"

}