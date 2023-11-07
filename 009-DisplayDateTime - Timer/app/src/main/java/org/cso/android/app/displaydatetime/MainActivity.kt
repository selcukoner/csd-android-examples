package org.cso.android.app.displaydatetime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.cso.android.app.displaydatetime.databinding.ActivityMainBinding
import org.csystem.android.util.datetime.di.module.formatter.annotation.LocalDateTimeFormatterInterceptor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mTimer: Timer

    private fun createTimerTask() = object : TimerTask(){
        override fun run() {
            mBinding.dateTime = formatter.format(LocalDateTime.now())
        }
    }

    @Inject
    @LocalDateTimeFormatterInterceptor
    lateinit var formatter : DateTimeFormatter

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }
    private fun initialize()
    {
        initBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()

    }

    override fun onStart() {
        try{

            super.onStart()
            mTimer = Timer()
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss")
            mTimer.scheduleAtFixedRate(createTimerTask(),0, 1000)
        }
        catch (ex: Throwable){
            Log.d("on-start", ex.message!!)
            Toast.makeText(this, "Problem occurred on start", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStop() {
        try {
            mTimer.cancel()
            super.onStop()

        }
        catch (ex: Throwable){
            Log.d("on-stop  ", ex.message!!)
            Toast.makeText(this, "Problem occurred on stop", Toast.LENGTH_LONG).show()
        }
    }




}