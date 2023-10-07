package com.cso.android.app.basicviewsoldapproach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

import com.cso.android.app.basicviewsoldapproach.global.alert.promptDecision
import com.cso.android.app.basicviewsoldapproach.global.alert.promptNotConfirmedDialog
import com.cso.android.app.basicviewsoldapproach.viewmodel.RegisterInfo
import java.lang.NumberFormatException
import java.time.DateTimeException
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private lateinit var mButtonRegister : Button
    private lateinit var mEditTextName: EditText
    private lateinit var mEditTextEmail: EditText
    private lateinit var mEditTextDay: EditText
    private lateinit var mEditTextMonth: EditText
    private lateinit var mEditTextYear: EditText

    private lateinit var mEditTextUserName:EditText
    private lateinit var mEditTextPassword:EditText
    private lateinit var mEditTextConfirmPassword: EditText


    private fun neutralButtonOnClickedCallcack()
    {
        mEditTextConfirmPassword.setText("")
    }
    private fun positiveButtonOnClickedCallcack()
    {
        finish()
    }
    private fun negativeButtonOnClickedCallcack()
    {
        Toast.makeText(this, R.string.message_continue,Toast.LENGTH_SHORT).show()
    }

    /*
    private fun promptNotConfirmedDialog()
    {
        AlertDialog.Builder(this)
            .setTitle(R.string.confirm_alert_dialog_title)
            .setMessage(R.string.message_password_not_confirmed)
            .setNeutralButton(R.string.ok_button_text){_,_ -> neutralButtonOnClickedCallcack()} // Butona basıldığında neolacağı
            .create()
            .show()
    }
    */
    private fun confirm(password: String, confirmPassword: String):Boolean
    {
        var result = true

        if(password != confirmPassword){
            promptNotConfirmedDialog(this,R.string.confirm_alert_dialog_title,
            R.string.message_password_not_confirmed,R.string.ok_button_text){_,_ -> neutralButtonOnClickedCallcack()}
            Toast.makeText(this, R.string.message_password_not_confirmed, Toast.LENGTH_LONG).show()
            result  = false
        }
        return result
    }

    private fun getRegisterInfo(): RegisterInfo?
    {
        val name = mEditTextName.text.toString()
        val email = mEditTextEmail.text.toString()

        val userName = mEditTextUserName.text.toString()
        val password = mEditTextPassword.text.toString()

        if(!confirm(password,  mEditTextConfirmPassword.text.toString()))
            return null

        val birthDate = LocalDate.of(mEditTextYear.text.toString().toInt(),
            mEditTextMonth.text.toString().toInt(),
            mEditTextDay.text.toString().toInt())


        return RegisterInfo(name, email, birthDate, userName, password)
    }

    private fun registerButtonClickedCallBack()
    {
        try {
            val registerInfo = getRegisterInfo()

            if(registerInfo != null)
                Toast.makeText(this,registerInfo?.toString(), Toast.LENGTH_LONG).show()

        }
        catch (ignore: NumberFormatException){
            Toast.makeText(this, R.string.message_number_format_exception,Toast.LENGTH_SHORT).show()
        }
        catch (ignore: DateTimeException){
            Toast.makeText(this, R.string.message_datetime_exception,Toast.LENGTH_SHORT).show()
        }
    }

    private fun closeButtonClickedCallBack()
    {
        promptDecision(this, R.string.confirm_close_alert_dialog_title,
            R.string.message_confirm_close,
            R.string.yes_button_text, R.string.no_button_text,{_,_ -> positiveButtonOnClickedCallcack()},
            {_,_ -> negativeButtonOnClickedCallcack()})
    }

    private fun initRegisterButton()
    {
       mButtonRegister = findViewById<Button>(R.id.mainActivityButtonRegister)
           .apply {setOnClickListener {registerButtonClickedCallBack()}}
    }
    private fun initCloseButton()
    {
        // activity i destroy yolunu başlatır pausedan
        findViewById<Button>(R.id.mainActivityButtonClose).apply { setOnClickListener { closeButtonClickedCallBack() } }
    }

    private fun initAcceptCheckbox()
    {



        findViewById<CheckBox>(R.id.mainActivityCheckboxAcceptConditions)
            .apply { setOnCheckedChangeListener{_, checked -> mButtonRegister.isEnabled = checked} } // butonu aktif eder
    }


    private fun initEditTexts()
    {
        mEditTextName = findViewById(R.id.mainActivityEditTextName)
        mEditTextEmail = findViewById(R.id.mainActivityEditTextEmail)
        mEditTextDay = findViewById(R.id.mainActivityEditTextDay)
        mEditTextMonth = findViewById(R.id.mainActivityEditTextMonth)
        mEditTextYear = findViewById(R.id.mainActivityEditTextYear)

        mEditTextUserName = findViewById(R.id.mainActivityEditTextUserName)
        mEditTextPassword = findViewById(R.id.mainActivityEditTextPassword)
        mEditTextConfirmPassword = findViewById(R.id.mainActivityEditTextConfirmPassword)
    }

    private fun initViews()
    {
        initEditTexts()
        initRegisterButton()
        initCloseButton()
        initAcceptCheckbox()
    }

    private fun initialize()
    {
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    override fun onStop() {
        Toast.makeText(this,"On Stop",Toast.LENGTH_SHORT).show()
        super.onStop()
    }

    override fun onPause() {
        Toast.makeText(this,"On Pause",Toast.LENGTH_SHORT).show()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"On Destroy",Toast.LENGTH_SHORT).show()
    }
}