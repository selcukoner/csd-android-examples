package org.cso.android.app.hilt.calculator.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.cso.android.app.hilt.calculator.IBinaryOperator
import org.cso.android.app.hilt.calculator.IntMultiplyOperation
import org.cso.android.app.hilt.calculator.di.module.annotation.IntMultiplyOperationInterceptor


@Module
@InstallIn(ActivityComponent::class)
abstract class IntMultiplyOperationModule {

    @Binds
    @IntMultiplyOperationInterceptor
    abstract fun bindIntMultiplyOperation(intMultiplyOperation: IntMultiplyOperation) : IBinaryOperator<Int>
}