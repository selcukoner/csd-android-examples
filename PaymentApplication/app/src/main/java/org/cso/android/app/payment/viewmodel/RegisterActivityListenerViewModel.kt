package org.cso.android.app.payment.viewmodel

import org.cso.android.app.payment.MainActivity
import org.cso.android.app.payment.RegisterActivity
import java.lang.ref.WeakReference

class RegisterActivityListenerViewModel(activity: RegisterActivity) {
    private val mWeakReference = WeakReference(activity)

    fun handleRegisterButton() = mWeakReference.get()?.registerButtonClicked()
}