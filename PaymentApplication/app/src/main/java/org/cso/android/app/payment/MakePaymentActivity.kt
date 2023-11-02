package org.cso.android.app.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.karandev.util.data.service.DataServiceException
import dagger.hilt.android.AndroidEntryPoint
import org.cso.android.app.payment.data.service.PaymentApplicationDataService
import org.cso.android.app.payment.data.service.dto.LoginInfoDTO
import org.cso.android.app.payment.data.service.dto.PaymentSaveDTO
import org.cso.android.app.payment.databinding.ActivityMakePaymentBinding
import org.cso.android.app.payment.global.getLoginInfo
import org.cso.android.app.payment.global.keys.LOGIN_INFO
import org.cso.android.app.payment.viewmodel.MakePaymentActivityListenerViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MakePaymentActivity : AppCompatActivity() {

    private lateinit var mLoginInfo : LoginInfoDTO
    private lateinit var mBinding : ActivityMakePaymentBinding
    @Inject
    lateinit var paymentApplicationDataService: PaymentApplicationDataService

    private fun initLoginInfo()
    {
        mLoginInfo = getLoginInfo(intent)
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_make_payment)
        mBinding.viewModel  = MakePaymentActivityListenerViewModel(this)
        initLoginInfo()
        mBinding.paymentInfo = PaymentSaveDTO(mLoginInfo.username)
    }

    private fun initialize()
    {
        initBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()

    }

    fun payButtonClicked()
    {
        //check data for validation
        try {
            paymentApplicationDataService.savePayment(mBinding.paymentInfo!!)
        }
        catch (ex : DataServiceException){
            Log.d("data_service", ex.message!!)
        }
        catch (ex : Throwable){
            Log.d("any_service", ex.message!!)
        }
    }
    fun clearButtonClicked()
    {
    }
    fun closeButtonClicked()
    {
        finish()
    }
}