package com.cso.android.app.generator.random.viewmodel.listeners

import com.cso.android.app.generator.random.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenersViewModel (activity: MainActivity ) {
    private val mWeakReference = WeakReference(activity)

    fun handleGenerateButton() = mWeakReference.get()?.generateButtonClicked()
    fun handleSaveButton() = mWeakReference.get()?.saveButtonClicked()
    fun handleClearButton() = mWeakReference.get()?.clearButtonClicked()

    fun handleRadioGroup(checkedId: Int) = mWeakReference.get()?.radioButtonChecked(checkedId)
}