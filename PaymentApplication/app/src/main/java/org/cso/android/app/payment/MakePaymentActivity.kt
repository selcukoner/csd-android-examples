package org.cso.android.app.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.cso.android.app.data.service.dto.LoginInfoDTO
import org.cso.android.app.payment.global.keys.LOGIN_INFO

class MakePaymentActivity : AppCompatActivity() {

    private lateinit var mLoginInfo : LoginInfoDTO
    private fun initLoginInfo()
    {
        mLoginInfo =  if(android.os.Build.VERSION.SDK_INT < 33)
            intent.getSerializableExtra(LOGIN_INFO) as LoginInfoDTO
        else
            intent.getSerializableExtra(LOGIN_INFO, LoginInfoDTO::class.java)!!
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_payment)
    }
}