package org.cso.android.app.hilt.calculator.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.cso.android.app.hilt.calculator.IBinaryOperator
import org.cso.android.app.hilt.calculator.IntAddOperation
import org.cso.android.app.hilt.calculator.di.module.annotation.IntAddOperationInterceptor


@Module
@InstallIn(ActivityComponent::class)
abstract class IntAddOperationModule {

    @Binds
    @IntAddOperationInterceptor
    abstract fun bindIntAddOperation(intAddOperation: IntAddOperation) : IBinaryOperator<Int>
}