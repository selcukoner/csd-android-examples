package org.cso.android.app.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.cso.android.app.payment.data.service.dto.LoginInfoSaveDTO
import org.cso.android.app.payment.databinding.ActivityOperationsBinding
import org.cso.android.app.payment.global.getLoginInfo
import org.cso.android.app.payment.global.keys.LOGIN_INFO
import org.cso.android.app.payment.viewmodel.OperationsActivityListenerViewModel


class OperationsActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityOperationsBinding

    private lateinit var mLoginInfo : LoginInfoSaveDTO
    private fun initLoginInfo()
    {
        mLoginInfo = getLoginInfo(intent)
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_operations)
        mBinding.viewModel = OperationsActivityListenerViewModel(this)
    }

    private fun initialize()
    {
        initBinding()
        initLoginInfo()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun paymentButtonClicked()
    {
        Intent(this, PaymentActivity::class.java).apply {putExtra(LOGIN_INFO, mLoginInfo); startActivity(this) }
    }

    fun loginInformationButtonClicked()
    {
        Intent(this, LoginInformationActivity::class.java).apply {putExtra(LOGIN_INFO, mLoginInfo);  startActivity(this) }
    }

    fun closButtonClicked()
    {
        finish()
    }
}