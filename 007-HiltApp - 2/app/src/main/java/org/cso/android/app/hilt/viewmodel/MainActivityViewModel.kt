package org.cso.android.app.hilt.viewmodel

import org.cso.android.app.hilt.MainActivity
import java.lang.ref.WeakReference

class MainActivityViewModel(activity: MainActivity) {

    private val mWeakReference = WeakReference(activity)


}