package com.cso.android.app.viewsdatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import androidx.databinding.DataBindingUtil

import com.cso.android.app.viewsdatabinding.databinding.ActivityMainBinding
import com.cso.android.app.viewsdatabinding.global.INPUTTYPE_TEXT_PASSWORD_HIDE
import com.cso.android.app.viewsdatabinding.global.INPUTTYPE_TEXT_PASSWORD_SHOW

import com.cso.android.app.viewsdatabinding.viewmodel.MainActivityListenersViewModel
import com.cso.android.app.viewsdatabinding.viewmodel.RegisterInfo
import org.cso.android.app.viewsdatabinding.global.alert.promptDecision
import org.cso.android.app.viewsdatabinding.global.alert.promptNotConfirmedDialog
import java.lang.NumberFormatException
import java.time.DateTimeException
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding // viewBinding true yapılınca bu sınıf uretilir

    private lateinit var mMonths : Array<String>

    private fun neutralButtonOnClickedCallcack()
    {
        mBinding.confirmPassword = ""
    }
    private fun positiveButtonOnClickedCallcack()
    {
        finish()
    }
    private fun negativeButtonOnClickedCallcack()
    {
        Toast.makeText(this, R.string.message_continue,Toast.LENGTH_SHORT).show()
    }

    private fun confirm(password: String, confirmPassword: String):Boolean
    {
        var result = true

        if(password != confirmPassword){
            promptNotConfirmedDialog(this, R.string.confirm_alert_dialog_title,
                R.string.message_password_not_confirmed, R.string.ok_button_text
            ){ _, _ -> neutralButtonOnClickedCallcack()}
            Toast.makeText(this, R.string.message_password_not_confirmed, Toast.LENGTH_LONG).show()
            result  = false
        }
        return result
    }

    private fun getRegisterInfo(): RegisterInfo?
    {

        if(!confirm(mBinding.password!!, mBinding.confirmPassword!!))
            return null

        val name = mBinding.registerInfoViewModel!!.name
        val email = mBinding.registerInfoViewModel!!.email
        val userName = mBinding.registerInfoViewModel!!.userName
        val birthDate =LocalDate.of(mBinding.yearPos, mBinding.monthPos + 1, mBinding.dayPos)

        return RegisterInfo(name, email, birthDate, userName, mBinding.password!!)
    }

    private fun clearEditTexts()
    {
        mBinding.password = ""

        for(view in mBinding.mainActivityLayoutRegisterInfo.children){
            if (view is EditText)
                view.setText("")
        }
        mBinding.confirmPassword = ""
    }

    private fun setRegisterInfoVisibility(visibility: Int)
    {

        for (view in mBinding.mainActivityLayoutRegisterInfo.children){
            view.visibility = visibility
        }
    }

    private fun initBirthDateTexts(today: LocalDate)
    {
        mBinding.dayPos = today.dayOfMonth -1
        mBinding.monthPos = today.monthValue -1

    }

    private fun getDaysByMonthAndYear(monthPos: Int, yearPos: Int) : List<Int>
    {
        val max = LocalDate.of(yearPos, monthPos + 1, 1).minusDays(1).dayOfMonth

        return (1..max).toList()
    }


    private fun initBirthDateAdapters()
    {
        val today = LocalDate.now()
        val years = (today.year-100..today.minusYears(19).year).toList()

        mBinding.dayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, getDaysByMonthAndYear(today.monthValue, today.year))
        mBinding.monthAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mMonths)
        mBinding.yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)

        mBinding.yearPos = mBinding.yearAdapter!!.count - 1

        initBirthDateTexts(today)
    }

    private fun initEducationAdapter()
    {
        val educationInfo = resources.getStringArray(R.array.spinner_education_info)
        mBinding.educationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, educationInfo)
        mBinding.educationSelectedPos = 2

    }

    private fun initBirthDateSpinners()
    {
        initBirthDateAdapters()
    }
    private fun initViewModels()
    {
        mBinding.mainActivityViewModel = MainActivityListenersViewModel(this)
        mBinding.registerInfoViewModel = RegisterInfo()
    }
    private fun initData()
    {
        initViewModels()
        mBinding.show = true
        mBinding.passwordInputType = INPUTTYPE_TEXT_PASSWORD_HIDE
        mBinding.showPasswordButtonText = resources.getString(R.string.button_show_password_text)
        initEducationAdapter()
        initBirthDateSpinners()
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initData()
    }

    private fun initViews()
    {
        mMonths = resources.getStringArray(R.array.spinner_months)
        initBinding()
    }

    private fun initialize()
    {
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }


    private fun allowClosePositiveButtonClickedCallback(checked: Boolean)
    {
        clearButtonClicked()
        setRegisterInfoVisibility(View.GONE)
    }

    private fun allowShowPasswordNegativeButtonClickedCallback(checked: Boolean)
    {
        mBinding.allowShowPasswordChecked = false
    }

    fun openToggleButtonCheckedChanged(checked: Boolean)
    {
        if(!checked) {
            AlertDialog.Builder(this).setTitle(R.string.confirm_alert_dialog_title)
                .setMessage(R.string.message_register_allow_close)
                .setPositiveButton(R.string.yes_button_text){ _, _ -> allowClosePositiveButtonClickedCallback(checked)}
                .setNegativeButton(R.string.no_button_text) { _, _ -> mBinding.mainActivityToggleButtonOpen.performClick()}
                .setOnCancelListener{mBinding.mainActivityToggleButtonOpen.performClick()}
                .create()
                .show()
        }else
            setRegisterInfoVisibility (View.VISIBLE )


    }

    fun allowShowPasswordSwitchCheckedChanged(checked: Boolean)
    {
        if(!checked)
            return

        AlertDialog.Builder(this).setTitle(R.string.confirm_alert_dialog_title)
            .setMessage(R.string.message_enable_show_password)
            .setPositiveButton(R.string.yes_button_text){ _, _ -> }
            .setNegativeButton(R.string.no_button_text) { _, _ -> allowShowPasswordNegativeButtonClickedCallback(checked)}
            .setOnCancelListener{mBinding.allowShowPasswordChecked = false} // dialog dışında bir yere tıklandığında
            .create()
            .show()

    }


    fun showPasswordButtonClicked()
    {
        val show = mBinding.show as Boolean
        val resId = if (show) R.string.button_hide_password_text else R.string.button_show_password_text
        mBinding.showPasswordButtonText = resources.getString(resId)
        mBinding.passwordInputType = if(show) INPUTTYPE_TEXT_PASSWORD_SHOW else INPUTTYPE_TEXT_PASSWORD_HIDE

        mBinding.show = !show
    }


    fun registerButtonClicked()
    {
        try {
            val registerInfo = getRegisterInfo()

            if(registerInfo != null){
                Toast.makeText(this,registerInfo?.toString(), Toast.LENGTH_LONG).show()
                mBinding.result= registerInfo.toString()
            }

        }
        catch (ignore: NumberFormatException){
            Toast.makeText(this, R.string.message_number_format_exception,Toast.LENGTH_SHORT).show()
        }
        catch (ignore: DateTimeException){
            Toast.makeText(this, R.string.message_datetime_exception,Toast.LENGTH_SHORT).show()
        }
    }
    fun clearButtonClicked()
    {


        clearEditTexts()
        initBirthDateTexts(LocalDate.now())
        mBinding.accept = false
        mBinding.passwordInputType = INPUTTYPE_TEXT_PASSWORD_HIDE
        mBinding.showPasswordButtonText = resources.getString(R.string.button_show_password_text)
        mBinding.show = true
        mBinding.result = ""
        mBinding.allowShowPasswordChecked = false

        mBinding.mainActivityEditTextName.requestFocus()

    }
    fun closeButtonClicked()
    {
        promptDecision(this, R.string.confirm_close_alert_dialog_title,
            R.string.message_confirm_close,
            R.string.yes_button_text, R.string.no_button_text,{ _, _ -> positiveButtonOnClickedCallcack()},
            {_,_ -> negativeButtonOnClickedCallcack()})
    }

    fun educationSpinnerItemSelected(pos:Int)
    {
        Toast.makeText(this, mBinding.educationAdapter!!.getItem(pos), Toast.LENGTH_SHORT).show()
    }


    fun birthDateMonthSpinnerItemSelected(pos: Int)
    {
        mBinding.dayAdapter?.clear()

        val year = mBinding.yearAdapter!!.getItem(mBinding.yearPos)!!.toInt()
        mBinding.dayAdapter?.addAll(getDaysByMonthAndYear(pos + 1,year))
    }
    fun birthDateYearSpinnerItemSelected(pos: Int)
    {
        mBinding.dayAdapter?.clear()
        val year = mBinding.yearAdapter!!.getItem(pos)!!.toInt()
        mBinding.dayAdapter?.addAll(getDaysByMonthAndYear(mBinding.monthPos + 1, year))
    }
}
