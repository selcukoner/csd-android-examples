package org.cso.android.app.multipleactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.cso.android.app.multipleactivity.databinding.ActivityMainBinding
import org.cso.android.app.multipleactivity.viewmodel.MainActivityListenersViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private fun initialize()
    {
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mBinding.viewModel = MainActivityListenersViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun registerButtonClicked()
    {
        startActivity(Intent(this,RegisterActivity::class.java))
        Toast.makeText(this,"register",Toast.LENGTH_SHORT).show()
    }

    fun loginButtonClicked()
    {
        startActivity(Intent(this,LoginActivity::class.java))
    }

    fun closeButtonClicked()
    {
        finish()
    }



}