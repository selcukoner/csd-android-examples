package org.cso.android.app.payment.viewmodel;

import org.cso.android.app.payment.LoginInformationActivity;
import org.cso.android.app.payment.OperationsActivity;

import java.lang.ref.WeakReference;

public class LoginInformationActivityListenerViewModel {
    private final WeakReference<LoginInformationActivity> m_weakReference ;

    public LoginInformationActivityListenerViewModel(LoginInformationActivity activity)
    {
        m_weakReference = new WeakReference<>(activity);
    }

    public void handleSuccessLoginsButton()
    {
        m_weakReference.get().successLoginsButtonClicked();
    }
    public void handleFailLoginsInformationButton() {
        m_weakReference.get().failLoginsButtonClicked();
    }
    public void handleCloseButton()
    {
        m_weakReference.get().closeButtonClicked();
    }
}
