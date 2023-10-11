package org.cso.android.app.multipleactivity.viewmodel

import org.cso.android.app.multipleactivity.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenersViewModel(activity: MainActivity) {
    private val mWeakReference = WeakReference(activity)

    fun handleRegisterButton() = mWeakReference.get()?.registerButtonClicked()
    fun handleLoginButton() = mWeakReference.get()?.loginButtonClicked()
    fun handleCloseButton() = mWeakReference.get()?.closeButtonClicked()
}