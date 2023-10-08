package org.cso.android.app.multipleactivity

import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import org.cso.android.app.multipleactivity.binding.PaymentQuantityToStringConverter
import org.cso.android.app.multipleactivity.binding.PaymentUnitPriceToStringConverter
import org.cso.android.app.multipleactivity.databinding.ActivityPaymentBinding
import org.cso.android.app.multipleactivity.keys.LOGIN_INFO

import org.cso.android.app.multipleactivity.viewmodel.LoginInfo
import org.cso.android.app.multipleactivity.viewmodel.PaymentActivityListenersViewModel
import org.cso.android.app.multipleactivity.viewmodel.PaymentInfo

class PaymentActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityPaymentBinding


    fun checkFail(): Boolean
    {
        if(PaymentQuantityToStringConverter.fail){
            Toast.makeText(this, PaymentQuantityToStringConverter.failStr, Toast.LENGTH_SHORT).show()
        }

        if(PaymentUnitPriceToStringConverter.fail){
            Toast.makeText(this, PaymentUnitPriceToStringConverter.failStr, Toast.LENGTH_SHORT).show()
        }
        return PaymentQuantityToStringConverter.fail || PaymentUnitPriceToStringConverter.fail
    }


    fun calculateButtonClicked(){
        try {
            if(checkFail())
                return
            mBinding.result = ""

            mBinding.result = mBinding.paymentInfo.toString()
        }catch (ignore:Throwable){
            Toast.makeText(this, "Problem Occurred", Toast.LENGTH_SHORT).show()
        }

    }

    fun clearButtonClicked()
    {
        /*
        for (view in mBinding.paymentActivityProductInfo.children){
            if(view is EditText )
                view.setText("")
        }
        mBinding.paymentActivityTextView.text = ""
        */
        mBinding.paymentInfo = PaymentInfo()
        mBinding.result = ""
    }


    fun exitButtonClicked()
    {
        TODO("NOT implemented yet")
    }

    fun closeButtonClicked()
    {
        AlertDialog.Builder(this)
            .setTitle(R.string.alert_dialog_close_title)
            .setMessage(R.string.alert_dialog_close_message_text)
            .setPositiveButton(R.string.alert_dialog_close_positive_button_text, {_,_ -> finish()})
            .setNegativeButton(R.string.alert_dialog_close_negative_button_text, {_,_ -> })
            .create()
            .show()
    }

    private fun initViewModels()
    {
        mBinding.viewModel = PaymentActivityListenersViewModel(this)
        mBinding.loginInfo = when{
            android.os.Build.VERSION.SDK_INT <= VERSION_CODES.TIRAMISU -> intent.getSerializableExtra(LOGIN_INFO) as LoginInfo
            else -> intent.getSerializableExtra(LOGIN_INFO, LoginInfo::class.java)
        }
        mBinding.paymentInfo = PaymentInfo()
        mBinding.result =""

        PaymentQuantityToStringConverter.failStr = "Invalid Quantity"
        PaymentUnitPriceToStringConverter.failStr = "Invalid unit price"
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_payment)
        initViewModels()

    }

    private fun initialize()
    {
        initBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
        Toast.makeText(this,mBinding.loginInfo!!.password, Toast.LENGTH_SHORT).show()
    }

}