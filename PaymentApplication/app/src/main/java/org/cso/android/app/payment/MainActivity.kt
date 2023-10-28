package org.cso.android.app.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.cso.android.app.data.service.PaymentApplicationDataService
import org.cso.android.app.data.service.dto.UserSaveDTO
import org.cso.android.app.data.service.mapper.IUserMapper
import org.cso.android.app.payment.databinding.ActivityMainBinding
import org.cso.android.app.payment.viewmodel.MainActivityListenerViewModel
import java.time.LocalDate
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun initialize()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityListenerViewModel(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()

    }

    fun registerButtonClicked()
    {
        Intent(this,RegisterActivity::class.java).apply { startActivity(this)}
    }
    fun loginButtonClicked()
    {
        Intent(this,LoginActivity::class.java).apply { startActivity(this)}
    }

}