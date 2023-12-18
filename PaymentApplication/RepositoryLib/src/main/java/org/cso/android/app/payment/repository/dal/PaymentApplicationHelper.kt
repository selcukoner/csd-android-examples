package org.cso.android.app.payment.repository.dal

import com.karandev.util.data.repository.exception.RepositoryException
import org.cso.android.app.payment.repository.dao.ILoginInfoDao
import org.cso.android.app.payment.repository.dao.IPaymentDao
import org.cso.android.app.payment.repository.dao.IUserDao
import org.cso.android.app.payment.repository.entity.LoginInfo
import org.cso.android.app.payment.repository.entity.Payment
import org.cso.android.app.payment.repository.entity.User
import org.cso.android.app.payment.repository.entity.join.UserToPayments

import java.io.IOException
import javax.inject.Inject

class PaymentApplicationHelper @Inject constructor() {
    @Inject
    lateinit var userDao: IUserDao

    @Inject
    lateinit var loginInfoDao: ILoginInfoDao

    @Inject
    lateinit var paymentDao: IPaymentDao

    fun saveUser(user: User)
    {
        try {
            userDao.save(user)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.saveUser", ex)
        }
    }

    fun existsUserByUserName(username: String?): Boolean
    {
        try {
            return userDao.existById(username!!)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.existsUserByUserName", ex)
        }
    }

    fun existsUserByUserNameAndPassword(username: String, password: String): Boolean
    {
        try {
            return userDao.existsByUserNameAndPassword(username, password)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.existsUserByUserNameAndPassword", ex)
        }

    }

    fun findUserByUserNameAndPassword(username: String, password: String): User?
    {
        try {
            return userDao.findByUserNameAndPassword(username, password)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.findUserByUserNameAndPassword", ex)
        }

    }


    fun findLoginInfoByUserName(username: String): List<LoginInfo>
    {
        try {
            return loginInfoDao.findByUserName(username)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.findLoginInfoByUserName", ex)
        }
    }

    fun findSuccessLoginInfoByUserName(username: String): List<LoginInfo>
    {
        try {
            return loginInfoDao.findSuccessByUserName(username)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.findSuccessLoginInfoByUserName", ex)
        }
    }

    fun findFailLoginInfoByUserName(username: String): List<LoginInfo>
    {
        try {
            return loginInfoDao.findFailsByUserName(username)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.findFailLoginInfoByUserName", ex)
        }
    }

    fun saveLoginInfo(loginInfo: LoginInfo)
    {
        try {
            loginInfoDao.save(loginInfo)
        }
        catch (ex: IOException){
            throw RepositoryException("PaymentApplicationHelper.saveLoginInfo", ex)
        }
    }

    /*
    fun findPaymentByUserName(username: String): List<UserToPayments>
    {
        try {
            return paymentDao.findByUserName(username)
        }
        catch (ex: Throwable){
            throw RepositoryException("PaymentApplicationHelper.findPaymentByUserName", ex)
        }
    }
*/
    fun savePayment(payment: Payment)
    {
        try {
            return paymentDao.save(payment)

        }
        catch (ex: IOException){
            throw RepositoryException("PaymentApplicationHelper.savePayment", ex)
        }
    }

}