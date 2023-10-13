package org.cso.android.app.multipleactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import org.cso.android.app.multipleactivity.databinding.ActivityRegisterBinding
import org.cso.android.app.multipleactivity.keys.EXIT
import org.cso.android.app.multipleactivity.viewmodel.RegisterActivityListenersViewModel
import org.cso.android.app.multipleactivity.viewmodel.RegisterInfo

class RegisterActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityRegisterBinding

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
    fun registerButtonClicked()
    {

        mBinding.resultText = mBinding.registerInfo.toString()
    }

    fun closeButtonClicked()
    {
        finish()
    }

    private fun clearResultText()
    {
        mBinding.resultText = ""
    }
    fun clearButtonClicked()
    {
        mBinding.registerInfo = RegisterInfo()
        mBinding.confirmPasword =""
        clearResultText()
    }

    private fun initViewModels()
    {
        mBinding.registerInfo = RegisterInfo()
        mBinding.viewModel = RegisterActivityListenersViewModel(this)
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        initViewModels()
    }
    private fun initialize()
    {
        initBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }
}