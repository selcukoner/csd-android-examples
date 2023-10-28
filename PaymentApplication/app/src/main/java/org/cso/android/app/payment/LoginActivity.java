package org.cso.android.app.payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.karandev.util.data.service.DataServiceException;

import org.cso.android.app.data.service.PaymentApplicationDataService;
import org.cso.android.app.data.service.dto.LoginInfoDTO;
import org.cso.android.app.payment.databinding.ActivityLoginBinding;
import org.cso.android.app.payment.viewmodel.LoginActivityListenerViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding m_binding;

    @Inject
    PaymentApplicationDataService dataService;


    private void initBinding()
    {
        m_binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        m_binding.setViewModel(new LoginActivityListenerViewModel(this));
        m_binding.setLoginInfo(new LoginInfoDTO());
    }

    private void init()
    {
        initBinding();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void loginButtonClicked()
    {
        try {
            if(dataService.checkAndSaveLoginInfo(m_binding.getLoginInfo())){
                Toast.makeText(this, "Access granted", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, OperationsActivity.class));
            }else{
                Toast.makeText(this, "Access denied", Toast.LENGTH_LONG).show();
            }

        }
        catch (DataServiceException ex){
        Toast.makeText(this, "Data Problem:" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        catch (Throwable ignoe){
        Toast.makeText(this, "Problem Occured. Try again later", Toast.LENGTH_LONG).show();
        }
    }

}