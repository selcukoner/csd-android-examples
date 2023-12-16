package org.cso.android.app.payment.repository.entity.join

import androidx.room.Embedded
import androidx.room.Relation
import org.cso.android.app.payment.repository.entity.LoginInfo
import org.cso.android.app.payment.repository.entity.User

//one to many relation
data class UserToLoginInfo(@Embedded val user: User,
                           @Relation(parentColumn = "username", entityColumn = "username") var loginInfoList: List<LoginInfo>) //primary key - foreign key relation
