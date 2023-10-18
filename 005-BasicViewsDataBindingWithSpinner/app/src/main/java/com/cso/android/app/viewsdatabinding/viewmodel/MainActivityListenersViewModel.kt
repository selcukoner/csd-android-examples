package com.cso.android.app.viewsdatabinding.viewmodel

import com.cso.android.app.viewsdatabinding.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenersViewModel(val activity: MainActivity) {
    private val mWeakReference = WeakReference(activity)

    fun handleOpenToggleButton(checked: Boolean) = mWeakReference.get()?.openToggleButtonCheckedChanged(checked)
    fun handleShowPasswordButton() = mWeakReference.get()?.showPasswordButtonClicked()
    fun handleRegisterButton() = mWeakReference.get()?.registerButtonClicked()
    fun handleClearButton() = mWeakReference.get()?.clearButtonClicked()
    fun handleCloseButton() = mWeakReference.get()?.closeButtonClicked()
    fun handleAllowShowPasswordSwitch(checked: Boolean) = mWeakReference.get()?.allowShowPasswordSwitchCheckedChanged(checked)
    fun handleEducationSpinner(pos:Int) = mWeakReference.get()?.educationSpinnerItemSelected(pos)
}