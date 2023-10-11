package org.cso.android.app.multipleactivity.viewmodel

import org.cso.android.app.multipleactivity.PaymentActivity
import java.lang.ref.WeakReference

class PaymentActivityListenersViewModel( activity: PaymentActivity, ) {
    private val mWeakReference = WeakReference(activity)


    fun handleCalculateButton() = mWeakReference.get()?.calculateButtonClicked()

    fun handleClearButton() = mWeakReference.get()?.clearButtonClicked()

    fun handleCloseButton() = mWeakReference.get()?.closeButtonClicked()
    fun handleExitButton() = mWeakReference.get()?.exitButtonClicked()

}