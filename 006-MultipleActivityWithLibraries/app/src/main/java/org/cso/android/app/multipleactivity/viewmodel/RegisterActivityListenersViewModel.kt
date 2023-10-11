package org.cso.android.app.multipleactivity.viewmodel

import org.cso.android.app.multipleactivity.RegisterActivity
import java.lang.ref.WeakReference

class RegisterActivityListenersViewModel(activity: RegisterActivity) {
    private val mWeakReference = WeakReference(activity)

    fun handleRegisterButton() = mWeakReference.get()?.registerButtonClicked()
    fun handleClearButton() = mWeakReference.get()?.clearButtonClicked()
    fun handleCloseButton() = mWeakReference.get()?.closeButtonClicked()
}