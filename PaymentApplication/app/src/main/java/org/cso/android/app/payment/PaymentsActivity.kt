package org.cso.android.app.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.cso.android.app.payment.data.service.dto.LoginInfoDTO
import org.cso.android.app.payment.global.getLoginInfo

class PaymentsActivity : AppCompatActivity() {
    private lateinit var mLoginInfo : LoginInfoDTO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payments)
    }
    private fun initLoginInfo()
    {
        mLoginInfo = getLoginInfo(intent)
    }

}