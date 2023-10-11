package org.cso.android.app.multipleactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import org.cso.android.app.multipleactivity.databinding.ActivityMainBinding
import org.cso.android.app.multipleactivity.keys.EXIT
import org.cso.android.app.multipleactivity.keys.LOGIN_INFO
import org.cso.android.app.multipleactivity.keys.PRODUCT_NAME
import org.cso.android.app.multipleactivity.keys.TOTAL_PRICE
import org.cso.android.app.multipleactivity.viewmodel.MainActivityListenersViewModel
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mLauncher: ActivityResultLauncher<Intent>


    private fun activitiesCallback(result : ActivityResult)
    {
        if (result.resultCode != RESULT_OK){
            return
        }

        val data = result.data
        val exit = data?.getBooleanExtra(EXIT, false)

        if(exit == true){
            Intent().apply {
                putExtra(EXIT,true)
                setResult(RESULT_OK, this)
            }
            finish()
        }
    }

    private fun initPaymentActivityResultLauncher()
    {
        mLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){activitiesCallback(it)}
    }

    private fun initialize()
    {
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mBinding.viewModel = MainActivityListenersViewModel(this)
        initPaymentActivityResultLauncher()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun registerButtonClicked()
    {
        //startActivity(Intent(this,RegisterActivity::class.java))
        Intent(this,RegisterActivity::class.java).apply {
            mLauncher.launch(this)
        }
        Toast.makeText(this,"register",Toast.LENGTH_SHORT).show()
    }

    fun loginButtonClicked()
    {
        Intent(this,LoginActivity::class.java).apply {
            mLauncher.launch(this)
        }
        //startActivity(Intent(this,LoginActivity::class.java))
    }

    fun closeButtonClicked()
    {

        finish()
    }



}