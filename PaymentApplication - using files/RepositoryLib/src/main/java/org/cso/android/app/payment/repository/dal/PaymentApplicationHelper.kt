package org.cso.android.app.payment.repository.dal

import com.karandev.util.data.repository.exception.RepositoryException
import org.cso.android.app.payment.repository.ILoginInfoRepository
import org.cso.android.app.payment.repository.IPaymentRepository
import org.cso.android.app.payment.repository.IUserRepository
import org.cso.android.app.payment.repository.entity.LoginInfo
import org.cso.android.app.payment.repository.entity.Payment
import org.cso.android.app.payment.repository.entity.User
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

    fun existsUserByUserName(username: String?): Boolean
    {
        try {
            return userRepository.existsById(username)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.existsUserByUserName", ex)
        }
    }

    fun existsUserByUserNameAndPassword(username: String, password: String): Boolean
    {
        try {
            return userRepository.existsByUserNameAndPassword(username, password)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.existsUserByUserNameAndPassword", ex)
        }

    }

    fun findUserByUserNameAndPassword(username: String, password: String): User?
    {
        try {
            return userRepository.findByUserNameAndPassword(username, password)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.findUserByUserNameAndPassword", ex)
        }

    }


    fun findLoginInfoByUserName(username: String): List<LoginInfo>
    {
        try {
            return loginInfoRepository.findByUserName(username)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.findLoginInfoByUserName", ex)
        }
    }

    fun findSuccessLoginInfoByUserName(username: String): List<LoginInfo>
    {
        try {
            return loginInfoRepository.findSuccessByUserName(username)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.findSuccessLoginInfoByUserName", ex)
        }
    }

    fun findFailLoginInfoByUserName(username: String): List<LoginInfo>
    {
        try {
            return loginInfoRepository.findFailsByUserName(username)
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

    fun findPaymentByUserName(username: String): List<Payment>
    {
        try {
            return paymentRepository.findByUserName(username)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.findPaymentByUserName", ex)
        }
    }

    fun savePayment(payment: Payment) : Payment?
    {
        try {
            return paymentRepository.save(payment)
        }
        catch (ex: IOException){
            throw RepositoryException("PaymentApplicationHelper.savePayment", ex)
        }
    }

}