package org.cso.android.app.payment.repository.entity.join

import androidx.room.Embedded
import androidx.room.Relation
import org.cso.android.app.payment.repository.entity.Payment
import org.cso.android.app.payment.repository.entity.User

//one to many relation
data class UserToPayments(@Embedded val user: User,
                          @Relation(parentColumn = "username", entityColumn = "username") var payments: List<Payment>) //primary key - foreign key relation
