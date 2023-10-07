package org.cso.android.app.multipleactivity.viewmodel

import org.cso.android.app.multipleactivity.PaymentActivity

data class PaymentActivityViewModel(val activity: PaymentActivity,
                                    var productName:String ="",
                                    var productPrice:String="",
                                    var productAmount: String="",
                                    var resultStr:String="") {

    fun handleCalculateButton() = activity.calculateButtonClicked()

    fun handleClearButton() = activity.clearButtonClicked()

    fun handleExitButton() = activity.closeButtonClicked()

}