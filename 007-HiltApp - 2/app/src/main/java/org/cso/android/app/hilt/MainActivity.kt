package org.cso.android.app.hilt

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.cso.android.app.hilt.calculator.IBinaryOperator
import org.cso.android.app.hilt.calculator.di.module.annotation.IntAddOperationInterceptor
import org.cso.android.app.hilt.databinding.ActivityMainBinding
import org.cso.android.app.hilt.datetime.DateInfo
import org.cso.android.app.hilt.datetime.DateTimeInfo
import org.cso.android.app.hilt.datetime.TimeInfo
import org.cso.android.app.hilt.viewmodel.MainActivityListenerViewModel
import org.cso.android.app.hilt.viewmodel.OperationInfo
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityMainBinding

    @Inject
    lateinit var dateTimeInfo: DateTimeInfo

    @Inject
    lateinit var dateInfo: DateInfo

    @Inject
    lateinit var timeInfo: TimeInfo

    @Inject
    @IntAddOperationInterceptor
    lateinit var intBinaryOperator: IBinaryOperator<Int>

    private fun showDateTime()
    {

        var sb = StringBuilder()

        sb.append("DateTime:").append(dateTimeInfo.toString()).append("\n")
            .append("Date:").append(dateInfo.toString()).append("\n")
            .append("Time:").append(timeInfo.toString()).append("\n")

        Toast.makeText(this, sb, Toast.LENGTH_LONG).show()
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityListenerViewModel(this)
        mBinding.operationInfo = OperationInfo()
    }

    private fun initialize()
    {
        initBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()

    }

    override fun onResume() {
        showDateTime()
        super.onResume()
    }

    fun calculateButtonClicked()
    {
        if(intBinaryOperator.isValid(mBinding.operationInfo!!.op)){
            mBinding.resultText =  intBinaryOperator.applyAsInt(mBinding.operationInfo!!.first, mBinding.operationInfo!!.second).toString()
        }else{
            mBinding.resultText = "Invalid Operation"
        }
    }
}