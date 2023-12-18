package org.cso.android.app.payment.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.cso.android.app.payment.repository.converter.LocalDateConverter
import org.cso.android.app.payment.repository.converter.LocalDateTimeConverter
import org.cso.android.app.payment.repository.dao.ILoginInfoDao
import org.cso.android.app.payment.repository.dao.IPaymentDao
import org.cso.android.app.payment.repository.dao.IUserDao
import org.cso.android.app.payment.repository.entity.LoginInfo
import org.cso.android.app.payment.repository.entity.Payment
import org.cso.android.app.payment.repository.entity.User

@Database(entities = [User::class, Payment::class, LoginInfo::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class, LocalDateConverter::class)
abstract class PaymentApplicationDatabase : RoomDatabase() {
    abstract fun createUserDao() : IUserDao
    abstract fun createPaymentDao() : IPaymentDao
    abstract fun createLoginInfoDao() : ILoginInfoDao
}