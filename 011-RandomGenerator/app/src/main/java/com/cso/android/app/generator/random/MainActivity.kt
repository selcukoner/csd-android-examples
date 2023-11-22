package com.cso.android.app.generator.random

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.cso.android.app.generator.random.databinding.ActivityMainBinding
import com.cso.android.app.generator.random.string.generateRandomTextEN
import com.cso.android.app.generator.random.viewmodel.data.GenerateInfo
import com.cso.android.app.generator.random.viewmodel.listeners.MainActivityListenersViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityMainBinding
    private lateinit var mScheduledFuture: ScheduledFuture<*>


    @Inject
    lateinit var executorService : ScheduledExecutorService // thread pool


    private fun waitScheduler()
    {   // first thread waits the second thread(timer)
        try {
            mScheduledFuture.get(mBinding.generateInfo!!.period* mBinding.generateInfo!!.count, TimeUnit.SECONDS)
        }
        catch (_: TimeoutException){
            mScheduledFuture.cancel(false)
        }
    }

    private fun schedulerTrackCallback()
    {
        try {
            mBinding.enabled = false
            //second thread in the thread pool (timer)
            mScheduledFuture = executorService.scheduleAtFixedRate({ schedulerCallback() }, 0L, mBinding.generateInfo!!.period, TimeUnit.SECONDS)

        }
        catch (ex: Throwable){
            runOnUiThread{Toast.makeText(this, "Problem Occured in scheduler track thread ${ex.message}", Toast.LENGTH_LONG).show()}

        }
        finally {
            waitScheduler()
            mBinding.enabled = true
        }


    }

    private fun schedulerCallback()
    {
        //as adapter is also view
        runOnUiThread {
            try {
                mBinding.adapter!!.add(
                    generateRandomTextEN(
                        Random.nextInt(
                            mBinding.generateInfo!!.minimum,
                            mBinding.generateInfo!!.maximum + 1
                        )
                    )
                )

            } catch (ex: Throwable) {
                runOnUiThread {
                    Toast.makeText(
                        this,
                        "Problem Occured in scheduler ${ex.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun initViewModels()
    {
        mBinding.viewModel = MainActivityListenersViewModel(this)
        mBinding.generateInfo = GenerateInfo()
        mBinding.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<String>())
        mBinding.enabled = true
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
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

    fun generateButtonClicked() = executorService.execute { schedulerTrackCallback() } // first thread in the thread pool


    fun saveButtonClicked()
    {

    }

    fun clearButtonClicked()
    {
        mBinding.adapter!!.clear()
    }
}