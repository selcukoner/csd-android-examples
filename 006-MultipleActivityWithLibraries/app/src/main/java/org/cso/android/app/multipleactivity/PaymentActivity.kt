package org.cso.android.app.multipleactivity

import android.content.Intent
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import org.cso.android.app.multipleactivity.library.databinding.converter.PaymentQuantityToStringConverter
import org.cso.android.app.multipleactivity.library.databinding.converter.PaymentUnitPriceToStringConverter
import org.cso.android.app.multipleactivity.databinding.ActivityPaymentBinding
import org.cso.android.app.multipleactivity.keys.EXIT
import org.cso.android.app.multipleactivity.keys.LOGIN_INFO
import org.cso.android.app.multipleactivity.keys.PRODUCT_NAME
import org.cso.android.app.multipleactivity.keys.TOTAL_PRICE

import org.cso.android.app.multipleactivity.viewmodel.LoginInfo
import org.cso.android.app.multipleactivity.viewmodel.PaymentActivityListenersViewModel
import org.cso.android.app.multipleactivity.viewmodel.PaymentInfo

class PaymentActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityPaymentBinding


    private fun closeAlertDialogPositiveCallback()
    {
        if(mBinding.result!!.isNotEmpty())
            Intent().apply {
                putExtra(PRODUCT_NAME, mBinding.paymentInfo!!.name)
                putExtra(TOTAL_PRICE, mBinding.paymentInfo!!.total)
                setResult(RESULT_OK, this)
            }
        finish()
    }

    private fun checkFail(): Boolean
    {
        if(org.cso.android.app.multipleactivity.library.databinding.converter.PaymentQuantityToStringConverter.fail){
            Toast.makeText(this, org.cso.android.app.multipleactivity.library.databinding.converter.PaymentQuantityToStringConverter.failStr, Toast.LENGTH_SHORT).show()
        }

        if(org.cso.android.app.multipleactivity.library.databinding.converter.PaymentUnitPriceToStringConverter.fail){
            Toast.makeText(this, org.cso.android.app.multipleactivity.library.databinding.converter.PaymentUnitPriceToStringConverter.failStr, Toast.LENGTH_SHORT).show()
        }
        return org.cso.android.app.multipleactivity.library.databinding.converter.PaymentQuantityToStringConverter.fail || org.cso.android.app.multipleactivity.library.databinding.converter.PaymentUnitPriceToStringConverter.fail
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
        mBinding.paymentInfo = PaymentInfo()
        mBinding.result = ""
    }

    //exit button study case
    fun exitAlertDialogPositiveCallback()
    {
        Intent().apply {
            putExtra(EXIT,true)
            setResult(RESULT_OK, this)
        }
        finish()
    }

    fun exitButtonClicked()
    {
        AlertDialog.Builder(this)
            .setTitle(R.string.alert_dialog_close_title)
            .setMessage(R.string.alert_dialog_exit_message_text)
            .setPositiveButton(R.string.alert_dialog_close_positive_button_text, {_,_ -> exitAlertDialogPositiveCallback()})
            .setNegativeButton(R.string.alert_dialog_close_negative_button_text, {_,_ -> })
            .create()
            .show()
    }

    fun closeButtonClicked()
    {
        AlertDialog.Builder(this)
            .setTitle(R.string.alert_dialog_close_title)
            .setMessage(R.string.alert_dialog_close_message_text)
            .setPositiveButton(R.string.alert_dialog_close_positive_button_text, {_,_ -> closeAlertDialogPositiveCallback()})
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

        org.cso.android.app.multipleactivity.library.databinding.converter.PaymentQuantityToStringConverter.failStr = "Invalid Quantity"
        org.cso.android.app.multipleactivity.library.databinding.converter.PaymentUnitPriceToStringConverter.failStr = "Invalid unit price"
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