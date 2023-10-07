package org.cso.android.app.multipleactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.cso.android.app.multipleactivity.databinding.ActivityRegisterBinding
import org.cso.android.app.multipleactivity.viewmodel.RegisterActivityListenersViewModel
import org.cso.android.app.multipleactivity.viewmodel.RegisterInfo

class RegisterActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityRegisterBinding





    fun registerButtonClicked()
    {
        Toast.makeText(this, "${mBinding.registerInfo.toString()}", Toast.LENGTH_SHORT).show()

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