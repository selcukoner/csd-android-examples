package org.cso.android.app.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.karandev.util.data.service.DataServiceException
import dagger.hilt.android.AndroidEntryPoint
import org.cso.android.app.data.service.PaymentApplicationDataService
import org.cso.android.app.data.service.dto.UserSaveDTO
import org.cso.android.app.data.service.mapper.IUserMapper
import org.cso.android.app.payment.databinding.ActivityRegisterBinding
import org.cso.android.app.payment.viewmodel.RegisterActivityListenerViewModel
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityRegisterBinding
    @Inject
    lateinit var dataService: PaymentApplicationDataService

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        mBinding.viewModel = RegisterActivityListenerViewModel(this)
        mBinding.user = UserSaveDTO()
    }

    private fun initialize()
    {
        initBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun registerButtonClicked()
    {
        try {
            val user = mBinding.user!!
            if(dataService.saveUser(user))
                Toast.makeText(this, "${user.userName} succesfully registered", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "${user.userName} can not be registered", Toast.LENGTH_LONG).show()
        }
        catch (ex: DataServiceException){
            Toast.makeText(this, "Data Problem:${ex.message}", Toast.LENGTH_LONG).show()
        }
        catch (ex: Throwable){
            Toast.makeText(this, "Problem Occured. Try again later", Toast.LENGTH_LONG).show()
        }
    }
}