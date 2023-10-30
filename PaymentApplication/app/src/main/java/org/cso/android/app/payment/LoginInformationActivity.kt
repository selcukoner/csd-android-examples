package org.cso.android.app.payment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.cso.android.app.payment.databinding.ActivityLoginInformationBinding
import org.cso.android.app.payment.viewmodel.LoginInformationActivityListenerViewModel

@AndroidEntryPoint
class LoginInformationActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginInformationBinding

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_information)
        mBinding.viewModel = LoginInformationActivityListenerViewModel(this)
    }

    private fun initialize()
    {
        initBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun successLoginsButtonClicked()
    {

    }

    fun  failLoginsButtonClicked()
    {

    }

    fun closeButtonClicked()
    {
        finish();
    }
}