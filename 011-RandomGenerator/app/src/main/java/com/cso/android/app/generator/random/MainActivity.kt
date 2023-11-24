package com.cso.android.app.generator.random

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.cso.android.app.generator.random.databinding.ActivityMainBinding
import com.cso.android.app.generator.random.global.what.WHAT_SCHEDULER_CALLBACK_EXCEPTION
import com.cso.android.app.generator.random.global.what.WHAT_SCHEDULER_TRACK_CALLBACK_EXCEPTION
import com.cso.android.app.generator.random.global.what.WHAT_TEXT_ADAPTER_ADD
import com.cso.android.app.generator.random.global.what.WHAT_WAIT_SCHEDULER_TIMEOUT
import com.cso.android.app.generator.random.string.generateRandomTextEN
import com.cso.android.app.generator.random.viewmodel.data.GenerateInfo
import com.cso.android.app.generator.random.viewmodel.listeners.MainActivityListenersViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference
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
    private lateinit var mHandler: Handler

    @Inject
    lateinit var executorService : ScheduledExecutorService // thread pool


    private class SchedulerHandler(activity: MainActivity) : Handler(Looper.myLooper()!!){
        private val mWeakReference = WeakReference(activity)

        private fun handleTextAdapterAdd(text:String)
        {
            mWeakReference.get()!!.mBinding.adapter!!.add(text)
        }

        private fun handleSchedulerTrackCallbackException(str:String)
        {
            Toast.makeText(mWeakReference.get(), "Problem Occurred in scheduler track thread ${str}", Toast.LENGTH_LONG).show()
        }

        private fun handleSchedulerCallbackException(str:String)
        {
            Toast.makeText(mWeakReference.get(), "Problem Occurred in scheduler thread ${str}", Toast.LENGTH_LONG).show()
        }

        private fun handleSchedulerTimeout()
        {
            Toast.makeText(mWeakReference.get(), "Texts generated!...", Toast.LENGTH_LONG).show()
        }
        override fun handleMessage(msg: Message)
        {
            when(msg.what){
                WHAT_TEXT_ADAPTER_ADD -> handleTextAdapterAdd(msg.obj as String)
                WHAT_SCHEDULER_TRACK_CALLBACK_EXCEPTION ->  handleSchedulerTrackCallbackException(msg.obj as String)
                WHAT_SCHEDULER_CALLBACK_EXCEPTION ->  handleSchedulerCallbackException(msg.obj as String)
                WHAT_WAIT_SCHEDULER_TIMEOUT ->  handleSchedulerTimeout()
            }
        }
    }


    private fun waitScheduler()
    {   // first thread waits the second thread(timer)
        try {
            mScheduledFuture.get(mBinding.generateInfo!!.period * mBinding.generateInfo!!.count* 1000 -10  , TimeUnit.MILLISECONDS)

        }
        catch (_: TimeoutException){
            mScheduledFuture.cancel(false)
            mHandler.sendEmptyMessage(WHAT_WAIT_SCHEDULER_TIMEOUT)
        }
    }

    private fun schedulerTrackCallback()
    {
        try {
            mBinding.enabled = false
            // create second thread in the thread pool (timer)
            mScheduledFuture = executorService.scheduleAtFixedRate({ schedulerCallback()  }, 0L, mBinding.generateInfo!!.period, TimeUnit.SECONDS)
        }
        catch (ex: Throwable){
             mHandler.sendMessage(mHandler.obtainMessage(WHAT_SCHEDULER_TRACK_CALLBACK_EXCEPTION, ex.message ))
        }
        finally {
            waitScheduler()
            mBinding.enabled = true
        }
    }

    private fun schedulerCallback()
    {
        try {
            val count = Random.nextInt(mBinding.generateInfo!!.minimum, mBinding.generateInfo!!.maximum + 1)
            val text = generateRandomTextEN(count)

            mHandler.sendMessage(Message.obtain(mHandler, WHAT_TEXT_ADAPTER_ADD, text))
        } catch (ex: Throwable) {
            mHandler.sendMessage(mHandler.obtainMessage(WHAT_SCHEDULER_CALLBACK_EXCEPTION, ex.message ))
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
        mHandler = SchedulerHandler(this)
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

    fun radioButtonChecked(checkedId: Int)
    {
        //Toast.makeText(this, "checked Id: $checkedId checkedButton:${mBinding.checkedButton}",Toast.LENGTH_LONG).show()

        if (checkedId == mBinding.mainActivityRadioButtonAddPeriodically.id)
      //      Toast.makeText(this,"Periodic", Toast.LENGTH_SHORT).show()
        else{
        //    Toast.makeText(this,"Last", Toast.LENGTH_SHORT).show()
        }
    }
}