package org.cso.android.app.payment.repository.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.cso.android.app.payment.repository.IPaymentRepository
import org.cso.android.app.payment.repository.PaymentRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PaymentRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPaymentRepository(paymentRepository: PaymentRepository): IPaymentRepository
}