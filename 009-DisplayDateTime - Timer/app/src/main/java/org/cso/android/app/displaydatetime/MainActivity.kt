package org.cso.android.app.displaydatetime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.cso.android.app.displaydatetime.databinding.ActivityMainBinding
import org.cso.android.app.displaydatetime.viewmodels.MainActivityListenersViewModel
import org.csystem.android.util.datetime.di.module.formatter.annotation.LocalDateTimeFormatterInterceptor
import org.csystem.android.util.datetime.di.module.formatter.annotation.LocalTimeFormatterInterceptor
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject
import kotlin.concurrent.thread

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mTimerDateTime: Timer
    private lateinit var mChronoTimer: Timer
    private lateinit var mClockThread : Thread

    private lateinit var mStoppableChronoTimer: Timer
    private var stoppableChronoSeconds = 0L

    @Inject
    @LocalDateTimeFormatterInterceptor
    lateinit var dateTimeFormatter : DateTimeFormatter
    @Inject
    @LocalTimeFormatterInterceptor
    lateinit var timeFormatter : DateTimeFormatter



    fun toggleButtonClickedCallback(checked: Boolean)
    {
        if (checked){
            mStoppableChronoTimer.cancel()
        }else{
            scheduleStoppableChronoTimer()
        }
    }

    private fun displayStoppableChronoDuration(seconds: Long)
    {
        val hour = seconds / 60 / 60
        val minute = seconds /60 %60
        val second = seconds %60

        mBinding.stoppableChronometer = "%02d:%02d:%02d".format(hour,minute,second)
    }
    private fun createStoppableChronometerTimerTask(): TimerTask = object : TimerTask(){
            override fun run() {
                displayStoppableChronoDuration(stoppableChronoSeconds++)
            }
        }

    private fun scheduleStoppableChronoTimer()
    {
        mStoppableChronoTimer = Timer()
        mStoppableChronoTimer.scheduleAtFixedRate(createStoppableChronometerTimerTask(),0, 1000)
    }

    private fun createChronoTimerTask() = object : TimerTask(){
        var seconds = 0L
        override fun run() {
            displayChronoDuration(seconds++)
        }
    }

    private fun createDateTimeTimerTask() = object : TimerTask(){
        override fun run() {
            mBinding.dateTime = dateTimeFormatter.format(LocalDateTime.now())
        }
    }
    private fun clockThreadCallback()
    {
        try {
            while (true){ // usage of Timer is better
                mBinding.time = timeFormatter.format(LocalTime.now())
                Thread.sleep(1000)
            }
        }
        catch (_ : InterruptedException){
            runOnUiThread{Toast.makeText(this, "Time to finish", Toast.LENGTH_LONG).show()}
        }
    }

    private fun startClock()
    {
     mClockThread = thread { clockThreadCallback() }
    }

    private fun scheduleChronoTimer()
    {
        mChronoTimer = Timer()
        mChronoTimer.scheduleAtFixedRate(createChronoTimerTask(),0, 1000)
    }

    private fun startAutoDisplayChronometer()
    {
        mBinding.mainActivityChronometerAutoDisplay.start()
    }

    private fun displayChronoDuration(seconds: Long)
    {
        val hour = seconds / 60 / 60
        val minute = seconds /60 %60
        val second = seconds %60

        mBinding.chronometer = "%02d:%02d:%02d".format(hour,minute,second)
    }
    private fun scheduleDateTimeTimer()
    {
        mTimerDateTime = Timer()
        mTimerDateTime.scheduleAtFixedRate(createDateTimeTimerTask(),0, 1000)
    }

    private fun initViewModel()
    {
        mBinding.viewmodel = MainActivityListenersViewModel(this)
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }
    private fun initialize()
    {
        initBinding()
        initViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()

    }

    override fun onStart() {
        try{

            super.onStart()
            scheduleDateTimeTimer()
            scheduleChronoTimer()
            scheduleStoppableChronoTimer()
            startClock()
            startAutoDisplayChronometer()
        }
        catch (ex: Throwable){
            Log.d("on-start", ex.message!!)
            Toast.makeText(this, "Problem occurred on start", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStop() {
        try {
            mTimerDateTime.cancel()
            mClockThread.interrupt()
            mChronoTimer.cancel()
            mBinding.mainActivityChronometerAutoDisplay.stop()
            super.onStop()

        }
        catch (ex: Throwable){
            Log.d("on-stop  ", ex.message!!)
            Toast.makeText(this, "Problem occurred on stop", Toast.LENGTH_LONG).show()
        }
    }

}