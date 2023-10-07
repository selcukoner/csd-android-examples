package com.cso.android.app.viewsviewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.children
import com.cso.android.app.viewsviewbinding.databinding.ActivityMainBinding
import com.cso.android.app.viewsviewbinding.global.alert.promptDecision
import com.cso.android.app.viewsviewbinding.global.alert.promptNotConfirmedDialog
import com.cso.android.app.viewsviewbinding.viewmodel.RegisterInfo
import java.lang.NumberFormatException
import java.time.DateTimeException
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding // viewBinding true yapılınca bu sınıf uretilir

    private fun neutralButtonOnClickedCallback()
    {
       mBinding.mainActivityEditTextConfirmPassword.setText("")
    }
    private fun positiveButtonOnClickedCallback()
    {
        finish()
    }
    private fun negativeButtonOnClickedCallback()
    {
        Toast.makeText(this, R.string.message_continue,Toast.LENGTH_SHORT).show()
    }

    private fun confirm(password: String, confirmPassword: String):Boolean
    {
        var result = true

        if(password != confirmPassword){
            promptNotConfirmedDialog(this,R.string.confirm_alert_dialog_title,
            R.string.message_password_not_confirmed,R.string.ok_button_text){_,_ -> neutralButtonOnClickedCallback()}
            Toast.makeText(this, R.string.message_password_not_confirmed, Toast.LENGTH_LONG).show()
            result  = false
        }
        return result
    }


    private fun getBirthDate():LocalDate
    {
        val year = mBinding.mainActivityEditTextYear.text.toString().toInt()
        val month = mBinding.mainActivityEditTextMonth.text.toString().toInt()
        val day = mBinding.mainActivityEditTextDay.text.toString().toInt()

        return LocalDate.of(year,month,day)
    }


    private fun getRegisterInfo(): RegisterInfo?
    {
        val name = mBinding.mainActivityEditTextName.text.toString()
        val email = mBinding.mainActivityEditTextEmail.text.toString()

        val userName = mBinding.mainActivityEditTextUserName.text.toString()
        val password = mBinding.mainActivityEditTextPassword.text.toString()

        if(!confirm(password,  mBinding.mainActivityEditTextConfirmPassword.text.toString()))
            return null

        val birthDate =getBirthDate()


        return RegisterInfo(name, email, birthDate, userName, password)
    }


    private fun registerButtonClickedCallBack()
    {
        try {
            val registerInfo = getRegisterInfo()

            if(registerInfo != null){
                Toast.makeText(this,registerInfo?.toString(), Toast.LENGTH_LONG).show()
                mBinding.mainActivityTextViewInformation.text = registerInfo.toString()
            }

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
            R.string.yes_button_text, R.string.no_button_text,{_,_ -> positiveButtonOnClickedCallback()},
            {_,_ -> negativeButtonOnClickedCallback()})
    }

    /* // bunu değiştirdi hoca
    private fun changeShowPasswordButtonText()
    {
        val showPasswordText = resources.getString(R.string.button_show_password_text)
        val text = mBinding.mainActivityButtonShowPassword.text.toString()
        var resID = if(text == showPasswordText) R.string.button_hide_password_text else R.string.button_show_password_text
        mBinding.mainActivityButtonShowPassword.setText(resID)
    }
*/

    private fun clearEditTexts()
    {
        mBinding.mainActivityEditTextPassword.setText("")

        for(view in mBinding.mainActivityLinearLayoutMain.children){
            if (view is EditText)
                view.setText("")
        }

        mBinding.mainActivityTextViewInformation.text = ""

    }

    private fun clearButtonClickedCallback()
    {

        clearEditTexts()
        initBirthDateTexts()
        mBinding.mainActivityCheckboxAcceptConditions.isChecked = false

        with(mBinding.mainActivityEditTextPassword ){
            inputType = INPUTTYPE_TEXT_PASSWORD_HIDE
            mBinding.mainActivityButtonShowPassword.setText(R.string.button_show_password_text)
            tag = false
        }

        mBinding.mainActivityEditTextName.requestFocus()
    }




    private fun showPasswordButtonClickedCallback()
    {


        with(mBinding.mainActivityButtonShowPassword){
            var show = tag as Boolean
            val resId = if (show) R.string.button_hide_password_text else R.string.button_show_password_text
            setText(resId)

            val showInput = INPUTTYPE_TEXT_PASSWORD_SHOW
            val hideInput = INPUTTYPE_TEXT_PASSWORD_HIDE
            mBinding.mainActivityEditTextPassword.inputType =
                if(show) showInput else hideInput
            tag = !show

        }

    }

    private fun acceptCheckBoxCheckedChangeCallback(checked :Boolean)
    {
        mBinding.mainActivityButtonRegister.isEnabled =checked
        mBinding.mainActivityTextViewInformation.text =""
    }

    private fun initShowPasswordButton()
    {

        mBinding.mainActivityButtonShowPassword.apply {
            tag = true
            setOnClickListener { showPasswordButtonClickedCallback() } }
    }

    private fun initClearButton()
    {
        mBinding.mainActivityButtonClear.apply { setOnClickListener { clearButtonClickedCallback() } }
    }



    private fun initRegisterButton()
    {
            mBinding.mainActivityButtonRegister.apply { setOnClickListener {registerButtonClickedCallBack()}}
    }

    private fun initCloseButton()
    {
        // activity i destroy yolunu başlatır pausedan
        mBinding.mainActivityButtonClose.apply { setOnClickListener { closeButtonClickedCallBack() } }

    }

    private fun initAcceptCheckbox()
    {
        mBinding.mainActivityCheckboxAcceptConditions
            .apply { setOnCheckedChangeListener{_, checked -> acceptCheckBoxCheckedChangeCallback(checked)} } // butonu aktif eder
    }

    private fun initBirthDateTexts()
    {
        val today = LocalDate.now()

        mBinding.mainActivityEditTextDay.setText(today.dayOfMonth.toString())
        mBinding.mainActivityEditTextMonth.setText(today.monthValue.toString())
        mBinding.mainActivityEditTextYear.setText(today.year.toString())


    }

    //bu bir kalıp. Id ler mBinding in referans veri elemanları oldu
    private fun initBinding()
    {
        mBinding = ActivityMainBinding.inflate(layoutInflater) // her layout un bir inflater ı var
        setContentView(mBinding.root) //
    }

    private fun initViews()
    {

        initBinding()
        initBirthDateTexts()
        initRegisterButton()
        initClearButton()
        initCloseButton()
        initAcceptCheckbox()
        initShowPasswordButton()
    }

    private fun initialize()
    {
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }


}