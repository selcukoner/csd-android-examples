package org.cso.android.app.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.cso.android.app.payment.data.service.dto.LoginInfoSaveDTO
import org.cso.android.app.payment.databinding.ActivityPaymentBinding
import org.cso.android.app.payment.global.getLoginInfo
import java.util.concurrent.ScheduledExecutorService
import javax.inject.Inject

class PaymentsActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityPaymentBinding

    private lateinit var mLoginInfo : LoginInfoSaveDTO

    @Inject
    lateinit var threadPool: ScheduledExecutorService
    private fun listPaymentsButtonClickedCallback()
    {

    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_payments)
    }

    private fun initialize()
    {
        initBinding()
        initLoginInfo()
    }

    private fun initLoginInfo()
    {
        mLoginInfo = getLoginInfo(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
    }

    fun listPaymentsButtonClicked() = threadPool.execute{ listPaymentsButtonClickedCallback()}

}