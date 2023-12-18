package org.cso.android.app.payment.repository.di.module.dao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.cso.android.app.payment.repository.dao.ILoginInfoDao
import org.cso.android.app.payment.repository.database.PaymentApplicationDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LoginInfoDaoModule {
    @Provides
    @Singleton
    fun createLoginInfoDao(paymentApplicationDatabase: PaymentApplicationDatabase): ILoginInfoDao
    {
        return paymentApplicationDatabase.createLoginInfoDao()
    }
}