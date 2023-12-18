package org.cso.android.app.payment.repository.di.module.dao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import org.cso.android.app.payment.repository.dao.IUserDao
import org.cso.android.app.payment.repository.database.PaymentApplicationDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserDaoModule {
    @Provides
    @Singleton
    fun createUserDao(paymentApplicationDatabase: PaymentApplicationDatabase): IUserDao
    {
        return paymentApplicationDatabase.createUserDao()
    }
}