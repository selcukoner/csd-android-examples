package org.cso.android.app.payment.repository.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.cso.android.app.payment.repository.IUserRepository
import org.cso.android.app.payment.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepository: UserRepository): IUserRepository
}