package org.cso.android.app.multipleactivity.viewmodel

import org.cso.android.app.multipleactivity.LoginActivity
import java.lang.ref.WeakReference

class LoginActivityListenersViewModel(activity: LoginActivity) {
    private val mWeakReference = WeakReference(activity)

    fun handleLoginButton() = mWeakReference.get()?.loginButtonClicked()
    fun handleExitButton() = mWeakReference.get()?.exitButtonClicked()
}