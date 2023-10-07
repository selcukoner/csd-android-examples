package org.cso.android.app.multipleactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.cso.android.app.multipleactivity.databinding.ActivityLoginBinding
import org.cso.android.app.multipleactivity.keys.LOGIN_INFO
import org.cso.android.app.multipleactivity.viewmodel.LoginActivityListenersViewModel

import org.cso.android.app.multipleactivity.viewmodel.LoginInfo
import java.time.LocalDateTime


class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginBinding

    /* alternative
    private fun startPaymentActivityCallBack(intent: Intent)
    {
        intent.putExtra(USER_NAME,mBinding.viewModel!!.username)
        startActivity(intent)

    }
    */

    private fun initViewModels()
    {
        mBinding.viewModel = LoginActivityListenersViewModel(this)
        mBinding.loginInfo = LoginInfo()
    }

    private fun initialize()
    {
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        initViewModels()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun loginButtonClicked()
    {
        Intent(this,PaymentActivity::class.java).apply {
            mBinding.loginInfo!!.loginDateTime = LocalDateTime.now()
            startActivity( putExtra(LOGIN_INFO,mBinding.loginInfo)) }
    }

}