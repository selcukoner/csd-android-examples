package org.cso.android.app.displaydatetime.viewmodels

import org.cso.android.app.displaydatetime.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenersViewModel(activity: MainActivity){
    private val mWeakReference= WeakReference(activity)

    fun handleToggleButton(checked: Boolean) = mWeakReference.get()?.toggleButtonClickedCallback(checked)
}