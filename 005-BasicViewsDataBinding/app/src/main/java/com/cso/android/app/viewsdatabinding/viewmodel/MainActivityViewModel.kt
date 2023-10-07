package com.cso.android.app.viewsdatabinding.viewmodel

import com.cso.android.app.viewsdatabinding.MainActivity

class MainActivityViewModel(val activity: MainActivity) {
    fun handleOpenToggleButton(checked: Boolean) = activity.openToggleButtonCheckedChanged(checked)

    fun handleShowPasswordButton() = activity.showPasswordButtonClicked()


    fun handleRegisterButton() = activity.registerButtonClicked()


    fun handleClearButton() = activity.clearButtonClicked()

    fun handleCloseButton() = activity.closeButtonClicked()

    fun handleAllowShowPasswordSwitch(checked: Boolean) = activity.allowShowPasswordSwitchCheckedChanged(checked)
}