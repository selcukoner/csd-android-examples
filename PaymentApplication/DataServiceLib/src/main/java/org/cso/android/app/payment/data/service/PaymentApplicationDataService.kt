package org.cso.android.app.payment.data.service

import com.karandev.util.data.repository.exception.RepositoryException
import com.karandev.util.data.service.DataServiceException
import org.cso.android.app.payment.data.service.dto.LoginInfoSaveDTO
import org.cso.android.app.payment.data.service.dto.PaymentSaveDTO
import org.cso.android.app.payment.data.service.dto.UserSaveDTO
import org.cso.android.app.payment.data.service.mapper.ILoginInfoMapper
import org.cso.android.app.payment.data.service.mapper.IPaymentMapper
import org.cso.android.app.payment.data.service.mapper.IUserMapper
import org.cso.android.app.payment.repository.dal.PaymentApplicationHelper
import org.cso.android.app.payment.data.service.dto.LoginInfoDTO
import java.lang.Exception
import javax.inject.Inject

class PaymentApplicationDataService @Inject constructor(
    paymentApplicationHelper: PaymentApplicationHelper,
    userMapper: IUserMapper,
    paymentMapper: IPaymentMapper,
    loginInfoMapper: ILoginInfoMapper
){
    private val mPaymentApplicationHelper = paymentApplicationHelper
    private val mUserMapper =userMapper
    private val mLoginInfoMapper = loginInfoMapper
    private val mPaymentMapper = paymentMapper
    fun checkAndSaveLoginInfo(loginInfoDTO: LoginInfoSaveDTO): Boolean
    {
        try {
            if(!mPaymentApplicationHelper.existsUserByUserName(loginInfoDTO.username))
                return false

            val loginInfo = mLoginInfoMapper.toLoginInfo(loginInfoDTO)

            if(mPaymentApplicationHelper.existsUserByUserNameAndPassword(loginInfoDTO.username,loginInfoDTO.password))
                mPaymentApplicationHelper.saveLoginInfo(loginInfo)
            else
                mPaymentApplicationHelper.saveLoginInfo(loginInfo.also { it.success =false })

            return loginInfo.success
        }
        catch (ex: RepositoryException){

            throw DataServiceException("PaymentApplicationDataService.checkAndSaveLoginInfo", ex.cause)
        }
        catch (ex: Throwable){
            throw DataServiceException("PaymentApplicationDataService.checkAndSaveLoginInfo", ex)
        }
    }

    fun findLoginInfoByUserName(username: String) : List<LoginInfoDTO>
    {
        try{
            return mPaymentApplicationHelper.findLoginInfoByUserName(username).toList()
                .map{mLoginInfoMapper.toLoginInfoDTO(it)}.toList()

        }
        catch (ex: RepositoryException){
            throw DataServiceException("PaymentApplicationDataService.findLoginInfoByUserName", ex.cause)
        }
        catch (ex: Throwable){
            throw DataServiceException("PaymentApplicationDataService.findLoginInfoByUserName", ex)
        }
    }

    fun findSuccessLoginInfoByUserName(username: String) : List<LoginInfoDTO>
    {
        try{
            return mPaymentApplicationHelper.findSuccessLoginInfoByUserName(username)
                .map { mLoginInfoMapper.toLoginInfoDTO(it) };
        }
        catch (ex: RepositoryException){
            throw DataServiceException("PaymentApplicationDataService.findSuccessLoginInfoByUserName", ex.cause)
        }
        catch (ex: Throwable){
            throw DataServiceException("PaymentApplicationDataService.findSuccessLoginInfoByUserName", ex)
        }
    }
    fun findFailLoginInfoByUserName(username: String) : List<LoginInfoDTO>
    {
        try{
            return mPaymentApplicationHelper.findFailLoginInfoByUserName(username)
                .map { mLoginInfoMapper.toLoginInfoDTO(it) };
        }
        catch (ex: RepositoryException){
            throw DataServiceException("PaymentApplicationDataService.findFailLoginInfoByUserName", ex.cause)
        }
        catch (ex: Throwable){
            throw DataServiceException("PaymentApplicationDataService.findFailLoginInfoByUserName", ex)
        }
    }

    fun saveUser(userSaveDTO: UserSaveDTO): Boolean
    {
        try {
            mPaymentApplicationHelper.saveUser(mUserMapper.toUser(userSaveDTO))
            return true
        }
        catch (ex: RepositoryException){
            throw DataServiceException("PaymentApplicationDataService.saveUser ${userSaveDTO.toString()}", ex.cause)
        }
        catch (ex: Throwable){
            throw DataServiceException("PaymentApplicationDataService.saveUser ", ex)
        }
    }

    fun savePayment(paymentSaveDTO: PaymentSaveDTO)
    {
        var result = false

        try {
            mPaymentApplicationHelper.savePayment(mPaymentMapper.toPayment(paymentSaveDTO)) != null
            result = true
        }
        catch (ex: RepositoryException){

            throw DataServiceException("PaymentApplicationDataService.savePayment", ex.cause)
        }
        catch (ex: Throwable){
            throw DataServiceException("PaymentApplicationDataService.savePayment", ex)
        }   
    }
}