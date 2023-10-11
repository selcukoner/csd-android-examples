package org.cso.android.app.multipleactivity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import org.cso.android.app.multipleactivity.databinding.ActivityLoginBinding
import org.cso.android.app.multipleactivity.keys.EXIT
import org.cso.android.app.multipleactivity.keys.LOGIN_INFO
import org.cso.android.app.multipleactivity.keys.PRODUCT_NAME
import org.cso.android.app.multipleactivity.keys.TOTAL_PRICE
import org.cso.android.app.multipleactivity.viewmodel.LoginActivityListenersViewModel

import org.cso.android.app.multipleactivity.viewmodel.LoginInfo
import java.time.LocalDateTime


class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginBinding
    private lateinit var mLauncher:ActivityResultLauncher<Intent>


    //exit button study case
    private fun exitAlertDialogPositiveCallback()
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

    private fun paymentActivityCallback(result : ActivityResult)
    {
        if (result.resultCode != RESULT_OK){
            return
        }

        val data = result.data
        val productName = data?.getStringExtra(PRODUCT_NAME)
        val totalPrice = data?.getDoubleExtra(TOTAL_PRICE, 0.0)
        val exit = data?.getBooleanExtra(EXIT, false)

        if(exit == true){
            Intent().apply {
                putExtra(EXIT,true)
                setResult(RESULT_OK, this)
            }
            finish()
        }

        "%.2f paid for %s".format(totalPrice,productName)
            .apply { Toast.makeText(this@LoginActivity,this, Toast.LENGTH_SHORT).show() }
    }

    private fun initPaymentActivityResultLauncher()
    {
      mLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){paymentActivityCallback(it)}
    }

    private fun initViewModels()
    {
        mBinding.viewModel = LoginActivityListenersViewModel(this)
        mBinding.loginInfo = LoginInfo()
    }

    private fun initialize()
    {
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        initViewModels()
        initPaymentActivityResultLauncher()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun loginButtonClicked()
    {
        Intent(this,PaymentActivity::class.java).apply {
            mBinding.loginInfo!!.loginDateTime = LocalDateTime.now()
            putExtra(LOGIN_INFO, mBinding.loginInfo)
            mLauncher.launch(this)
        }
    }

}