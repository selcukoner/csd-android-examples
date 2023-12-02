package org.cso.android.app.payment.viewmodel

import org.cso.android.app.payment.PaymentActivity
import org.cso.android.app.payment.PaymentsActivity
import java.lang.ref.WeakReference

class PaymentsActivityListenerViewModel(activity: PaymentsActivity) {
    private val m_weakReference = WeakReference(activity)

    fun handleListPaymentsButton()
    {
        m_weakReference.get()?.listPaymentsButtonClicked()
    }

}