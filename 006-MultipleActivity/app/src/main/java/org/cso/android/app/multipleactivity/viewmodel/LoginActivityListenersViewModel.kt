package org.cso.android.app.multipleactivity.viewmodel

import org.cso.android.app.multipleactivity.LoginActivity

data class LoginActivityListenersViewModel(val activity: LoginActivity) {

    fun handleLoginButton() = activity.loginButtonClicked()
}