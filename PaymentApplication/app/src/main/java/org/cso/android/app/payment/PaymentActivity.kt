package org.cso.android.app.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.cso.android.app.data.service.dto.LoginInfoDTO
import org.cso.android.app.payment.databinding.ActivityPaymentBinding
import org.cso.android.app.payment.global.keys.LOGIN_INFO
import org.cso.android.app.payment.viewmodel.PaymentActivityListenerViewModel

class PaymentActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityPaymentBinding
    private lateinit var mLoginInfo : LoginInfoDTO
    private fun initLoginInfo()
    {
        mLoginInfo =  if(android.os.Build.VERSION.SDK_INT < 33)
            intent.getSerializableExtra(LOGIN_INFO) as LoginInfoDTO
        else
            intent.getSerializableExtra(LOGIN_INFO, LoginInfoDTO::class.java)!!
    }
    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment)
        mBinding.viewModel = PaymentActivityListenerViewModel(this)
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

    fun makePaymentButtonClicked()
    {
        Intent(this,MakePaymentActivity::class.java).apply {putExtra(LOGIN_INFO,mLoginInfo);  startActivity(this) }
    }
    fun paymentsButtonClicked()
    {
        Intent(this,PaymentsActivity::class.java).apply { putExtra(LOGIN_INFO,mLoginInfo); startActivity(this) }
    }
    fun closeButtonClicked()
    {
        finish()
    }
}