package org.cso.android.app.payment.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.cso.android.app.payment.repository.entity.join.UserToPayments
import org.cso.android.app.payment.repository.entity.Payment

@Dao
interface IPaymentDao{
    @Query("SELECT * FROM users WHERE username = :username")
    fun findByUserName(username: String) : List<UserToPayments>

    @Insert
    fun save(payment: Payment)
}