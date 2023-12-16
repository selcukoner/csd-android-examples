package org.cso.android.app.payment.repository.di.module.dao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.cso.android.app.payment.repository.dao.IUserDao
import org.cso.android.app.payment.repository.database.PaymentApplicationDatabase
import javax.inject.Singleton


@Module
@InstallIn(ActivityComponent::class)
object UserDaoModule {
    @Provides
    fun createUserDao(paymentApplicationDatabase: PaymentApplicationDatabase): IUserDao
    {
        return paymentApplicationDatabase.createUserDao()
    }
}