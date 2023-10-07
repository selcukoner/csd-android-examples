package org.cso.android.app.multipleactivity

import android.content.Intent
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import org.cso.android.app.multipleactivity.databinding.ActivityMainBinding
import org.cso.android.app.multipleactivity.databinding.ActivityPaymentBinding
import org.cso.android.app.multipleactivity.keys.LOGIN_INFO

import org.cso.android.app.multipleactivity.viewmodel.LoginInfo
import org.cso.android.app.multipleactivity.viewmodel.MainActivityViewModel
import org.cso.android.app.multipleactivity.viewmodel.PaymentActivityViewModel
import org.cso.android.app.multipleactivity.viewmodel.PaymentInfo
import java.lang.NumberFormatException

class PaymentActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityPaymentBinding

    fun calculateButtonClicked(){
        try {
            mBinding.result = ""
            var pi = PaymentInfo(mBinding.viewModel!!.productName,
                mBinding.viewModel!!.productPrice.toDouble(),
                mBinding.viewModel!!.productAmount.toInt())

            mBinding.result = pi.toString()
        }catch (ignore:Throwable){
            Toast.makeText(this, "Problem Occurred", Toast.LENGTH_SHORT).show()
        }
    }

    fun clearButtonClicked()
    {

        for (view in mBinding.paymentActivityProductInfo.children){
            if(view is EditText )
                view.setText("")
        }
        mBinding.paymentActivityTextView.text = ""
    }

    fun closeButtonClicked()
    {
        AlertDialog.Builder(this)
            .setTitle(R.string.close_alert_dialog_title)
            .setMessage(R.string.alert_dialog_message_text)
            .setPositiveButton(R.string.alert_dialog_yes_button_text, {_,_ -> finish()})
            .setNegativeButton(R.string.alert_dialog_no_button_text, {_,_ -> })
            .create()
            .show()
    }

    private fun initViewModels()
    {
        mBinding.viewModel = PaymentActivityViewModel(this)
        mBinding.loginInfo = when{
            android.os.Build.VERSION.SDK_INT <= VERSION_CODES.TIRAMISU -> intent.getSerializableExtra(LOGIN_INFO) as LoginInfo
            else -> intent.getSerializableExtra(LOGIN_INFO, LoginInfo::class.java)
        }

        mBinding.result =""
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