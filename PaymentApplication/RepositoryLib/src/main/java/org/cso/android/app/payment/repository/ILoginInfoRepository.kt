package org.cso.android.app.payment.repository

import com.karandev.util.data.repository.ICrudRepository
import org.cso.android.app.payment.repository.entity.LoginInfo

interface ILoginInfoRepository : ICrudRepository<LoginInfo, Long>{
    fun findByUserNAme(userName: String) : List<LoginInfo>

    fun findSuccessByUserName(userName: String) : List<LoginInfo>
    fun findFailsByUserName(userName: String) : List<LoginInfo>

    fun findLastSuccessByUserName(userName: String) : List<LoginInfo>
    fun findLastFailByUserName(userName: String) : List<LoginInfo>
}