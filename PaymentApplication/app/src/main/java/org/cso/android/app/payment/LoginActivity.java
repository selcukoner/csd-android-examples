package org.cso.android.app.payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import org.cso.android.app.payment.databinding.ActivityLoginBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding m_binding;

    private void initBinding()
    {
        m_binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
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
}