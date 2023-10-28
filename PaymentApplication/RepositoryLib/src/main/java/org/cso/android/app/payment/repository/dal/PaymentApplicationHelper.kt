package org.cso.android.app.payment.repository.dal

import com.karandev.util.data.repository.exception.RepositoryException
import org.cso.android.app.payment.repository.ILoginInfoRepository
import org.cso.android.app.payment.repository.IPaymentRepository
import org.cso.android.app.payment.repository.IUserRepository
import org.cso.android.app.payment.repository.entity.LoginInfo
import org.cso.android.app.payment.repository.entity.User
import org.cso.android.app.payment.repository.global.USER_FILE
import java.io.IOException
import javax.inject.Inject

class PaymentApplicationHelper @Inject constructor() {
    @Inject
    lateinit var userRepository: IUserRepository

    @Inject
    lateinit var loginInfoRepository: ILoginInfoRepository

    @Inject
    lateinit var paymentRepository: IPaymentRepository

    fun saveUser(user: User) : User?
    {
        try {
            return userRepository.save(user)
        }
        catch (ex: IOException){
            throw RepositoryException("PaymentApplicationHelper.saveUser", ex)
        }
    }

    fun existsUserByUserName(userName: String?): Boolean
    {
        try {
            return userRepository.existsById(userName)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.existsUserByUserName", ex)
        }
    }

    fun existsUserByUserNameAndPassword(userName: String, password: String): Boolean
    {
        try {
            return userRepository.existsByUserNameAndPassword(userName, password)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.existsUserByUserNameAndPassword", ex)
        }

    }

    fun findUserByUserNameAndPassword(userName: String, password: String): User?
    {
        try {
            return userRepository.findByUserNameAndPassword(userName, password)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.findUserByUserNameAndPassword", ex)
        }

    }


    fun findLoginInfoByUserName(userName: String): List<LoginInfo>
    {
        try {
            return loginInfoRepository.findByUserNAme(userName)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.findLoginInfoByUserName", ex)
        }
    }

    fun findSuccessLoginInfoByUserName(userName: String): List<LoginInfo>
    {
        try {
            return loginInfoRepository.findByUserNAme(userName)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.findSuccessLoginInfoByUserName", ex)
        }
    }

    fun findFailLoginInfoByUserName(userName: String): List<LoginInfo>
    {
        try {
            return loginInfoRepository.findByUserNAme(userName)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.findFailLoginInfoByUserName", ex)
        }
    }

    fun saveLoginInfo(loginInfo: LoginInfo) : LoginInfo
    {
        try {
            return loginInfoRepository.save(loginInfo)
        }
        catch (ex: IOException){
            throw RepositoryException("PaymentApplicationHelper.saveLoginInfo", ex)
        }
    }


}