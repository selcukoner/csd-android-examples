package org.cso.android.app.payment.repository.di.module.dao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.cso.android.app.payment.repository.dao.IPaymentDao
import org.cso.android.app.payment.repository.database.PaymentApplicationDatabase


@Module
@InstallIn(ActivityComponent::class)
object PaymentDaoModule {
    @Provides
    fun createPaymentDao(paymentApplicationDatabase: PaymentApplicationDatabase): IPaymentDao
    {
        return paymentApplicationDatabase.createPaymentDao()
    }
}