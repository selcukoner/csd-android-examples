package org.cso.android.activity.viewmodel

import org.cso.android.activity.LoginActivity
import java.lang.ref.WeakReference

internal class LoginActivityListenersViewModel(activity: LoginActivity) {
    private val mWeakReference = WeakReference(activity)

    fun handleLoginButton() = mWeakReference.get()?.loginButtonClicked()

}