package org.cso.android.app.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.karandev.util.data.service.DataServiceException
import dagger.hilt.android.AndroidEntryPoint
import org.cso.android.app.payment.data.service.PaymentApplicationDataService
import org.cso.android.app.payment.data.service.dto.LoginInfoSaveDTO
import org.cso.android.app.payment.data.service.dto.PaymentSaveDTO
import org.cso.android.app.payment.databinding.ActivityMakePaymentBinding
import org.cso.android.app.payment.global.getLoginInfo
import org.cso.android.app.payment.viewmodel.MakePaymentActivityListenerViewModel
import java.util.concurrent.ScheduledExecutorService
import javax.inject.Inject

@AndroidEntryPoint
class MakePaymentActivity : AppCompatActivity() {

    private lateinit var mLoginInfo : LoginInfoSaveDTO
    private lateinit var mBinding : ActivityMakePaymentBinding
    @Inject
    lateinit var paymentApplicationDataService: PaymentApplicationDataService

    @Inject
    lateinit var threadPool: ScheduledExecutorService

    fun payButtonClickedCallback()
    {
        //check data for validation
        try {
            paymentApplicationDataService.savePayment(mBinding.paymentInfo!!)
            runOnUiThread { Toast.makeText(this, "Paid successfully", Toast.LENGTH_SHORT).show() }
        }
        catch (ex : DataServiceException){
            runOnUiThread { Toast.makeText(this, "Data problem  occurred", Toast.LENGTH_SHORT).show() }
            Log.d("data_service", ex.message!!)
        }
        catch (ex : Throwable){
            runOnUiThread { Toast.makeText(this, "Problem occurred", Toast.LENGTH_SHORT).show() }
            Log.d("any_service", ex.message!!)
        }
    }


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

    fun payButtonClicked() = threadPool.execute {payButtonClickedCallback() } // threadPool.execute(::payButtonClickedCallback )

    fun clearButtonClicked()
    {
        for (index in 0..<mBinding.makePaymentActivityLinearLayoutMain.childCount){
            val view = mBinding.makePaymentActivityLinearLayoutMain.getChildAt(index)

            if (view is EditText)
                view.setText("")
        }
    }
    fun closeButtonClicked()
    {
        finish()
    }
}