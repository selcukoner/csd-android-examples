package org.cso.android.app.data.service

import com.karandev.util.data.repository.exception.RepositoryException
import com.karandev.util.data.service.DataServiceException
import org.cso.android.app.data.service.dto.LoginInfoDTO
import org.cso.android.app.data.service.dto.LoginInfoStatusDTO
import org.cso.android.app.data.service.dto.PaymentSaveDTO
import org.cso.android.app.data.service.dto.UserSaveDTO
import org.cso.android.app.data.service.mapper.ILoginInfoMapper
import org.cso.android.app.data.service.mapper.IPaymentMapper
import org.cso.android.app.data.service.mapper.IUserMapper
import org.cso.android.app.payment.repository.dal.PaymentApplicationHelper
import javax.inject.Inject

class PaymentApplicationDataService @Inject constructor(
    paymentApplicationHelper: PaymentApplicationHelper,
    userMapper:IUserMapper,
    paymentMapper: IPaymentMapper,
    loginInfoMapper: ILoginInfoMapper ){
    private val mPaymentApplicationHelper = paymentApplicationHelper
    private val mUserMapper =userMapper
    private val mLoginInfoMapper = loginInfoMapper
    private val mPaymentMapper = paymentMapper
    fun checkAndSaveLoginInfo(loginInfoDTO: LoginInfoDTO): Boolean
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

    fun findLoginInfoByUserName(username: String) : List<LoginInfoStatusDTO>
    {
        try{
            return mPaymentApplicationHelper.findLoginInfoByUserName(username)
                .map { mLoginInfoMapper.toLoginInfoStatusDTO(it) };
        }
        catch (ex: RepositoryException){
            throw DataServiceException("PaymentApplicationDataService.findLoginInfoByUserName", ex.cause)
        }
        catch (ex: Throwable){
            throw DataServiceException("PaymentApplicationDataService.findLoginInfoByUserName", ex)
        }
    }

    fun findSuccessLoginInfoByUserName(username: String) : List<LoginInfoStatusDTO>
    {
        try{
            return mPaymentApplicationHelper.findSuccessLoginInfoByUserName(username)
                .map { mLoginInfoMapper.toLoginInfoStatusDTO(it) };
        }
        catch (ex: RepositoryException){
            throw DataServiceException("PaymentApplicationDataService.findSuccessLoginInfoByUserName", ex.cause)
        }
        catch (ex: Throwable){
            throw DataServiceException("PaymentApplicationDataService.findSuccessLoginInfoByUserName", ex)
        }
    }
    fun findFailLoginInfoByUserName(username: String) : List<LoginInfoStatusDTO>
    {
        try{
            return mPaymentApplicationHelper.findFailLoginInfoByUserName(username)
                .map { mLoginInfoMapper.toLoginInfoStatusDTO(it) };
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
        var result = false

        try {
            mPaymentApplicationHelper.saveUser(mUserMapper.toUser(userSaveDTO)) != null
            result = true
        }
        catch (ex: RepositoryException){

            throw DataServiceException("PaymentApplicationDataService.saveUser", ex.cause)
        }
        catch (ex: Throwable){
            throw DataServiceException("PaymentApplicationDataService.saveUser", ex)
        }
        return result
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