package org.cso.android.app.payment.repository.di.module.dao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import org.cso.android.app.payment.repository.dao.IPaymentDao
import org.cso.android.app.payment.repository.database.PaymentApplicationDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PaymentDaoModule {
    @Provides
    @Singleton
    fun createPaymentDao(paymentApplicationDatabase: PaymentApplicationDatabase): IPaymentDao
    {
        return paymentApplicationDatabase.createPaymentDao()
    }
}