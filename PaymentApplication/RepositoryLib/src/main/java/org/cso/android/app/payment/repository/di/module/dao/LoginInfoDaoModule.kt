package org.cso.android.app.payment.repository.di.module.dao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.cso.android.app.payment.repository.dao.ILoginInfoDao
import org.cso.android.app.payment.repository.dao.IPaymentDao
import org.cso.android.app.payment.repository.database.PaymentApplicationDatabase


@Module
@InstallIn(ActivityComponent::class)
object LoginInfoDaoModule {
    @Provides
    fun createLoginInfoDao(paymentApplicationDatabase: PaymentApplicationDatabase): ILoginInfoDao
    {
        return paymentApplicationDatabase.createLoginInfo()
    }
}