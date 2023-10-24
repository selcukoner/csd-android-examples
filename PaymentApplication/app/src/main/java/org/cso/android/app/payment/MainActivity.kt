package org.cso.android.app.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.cso.android.app.data.service.PaymentApplicationDataService
import org.cso.android.app.data.service.dto.UserSaveDTO
import org.cso.android.app.payment.databinding.ActivityMainBinding
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    @Inject
    lateinit var dataService: PaymentApplicationDataService

    private fun initialize()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        saveUser()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun saveUser()
    {
        var userSaveDTO = UserSaveDTO("Selçuk","selcuk1234","Cihan","Selçuk", LocalDate.of(1990,1,1))

        dataService.saveUser(userSaveDTO)
    }
}