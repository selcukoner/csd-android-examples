package com.cso.android.app.a002_activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //cevrildiğinde bundle null olmaz
    fun displayInitMessage(bundle:Bundle?)
    {
        var message = if (bundle == null) "First Created" else "Recreated by the system"

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }


    // gorunmeden cagrılır
    override fun onCreate(savedInstanceState: Bundle?) {
        displayInitMessage(savedInstanceState)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //tasarımı inflate eder
        Toast.makeText(this,R.string.on_create_message,Toast.LENGTH_SHORT).show()
        println("on Create print")
        //makeText factory method
    }
    // toast mesajları asenkron calışır
    // gorunmeden cagrılır
    override fun onStart() {
        super.onStart()
        Toast.makeText(this,R.string.on_start_message,Toast.LENGTH_SHORT).show()
        println("on Start print")
    }
    // gorunur gorunmez çagrılır
    override fun onResume() {
        super.onResume()
        Toast.makeText(this,R.string.on_resume_message,Toast.LENGTH_SHORT).show()
        println("on resume print")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this,R.string.on_pause_message,Toast.LENGTH_SHORT).show()
        println("on pause print")
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this,R.string.on_stop_message,Toast.LENGTH_SHORT).show()
        println("on stop print")
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this,R.string.on_restart_message, Toast.LENGTH_SHORT).show()
        println("on restart print")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,R.string.on_destroy_message,Toast.LENGTH_SHORT).show()
        println("on destroy print")
    }


}