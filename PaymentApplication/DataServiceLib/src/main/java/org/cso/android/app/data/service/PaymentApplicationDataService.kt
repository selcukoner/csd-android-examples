package org.cso.android.app.data.service

import com.karandev.util.data.repository.exception.RepositoryException
import com.karandev.util.data.service.DataServiceException
import org.cso.android.app.data.service.dto.LoginInfoDTO
import org.cso.android.app.data.service.dto.UserSaveDTO
import org.cso.android.app.data.service.mapper.ILoginInfoMapper
import org.cso.android.app.data.service.mapper.IUserMapper
import org.cso.android.app.data.service.mapper.di.module.annotation.UserMapperInterceptor
import org.cso.android.app.payment.repository.dal.PaymentApplicationHelper
import org.cso.android.app.payment.repository.entity.LoginInfo
import javax.inject.Inject

class PaymentApplicationDataService @Inject constructor(
    paymentApplicationHelper: PaymentApplicationHelper,
    @UserMapperInterceptor userMapper:IUserMapper,
    loginInfoMapper: ILoginInfoMapper ){
    private val mPaymentApplicationHelper = paymentApplicationHelper
    private val mUserMapper =userMapper
    private val mLoginInfoMapper = loginInfoMapper

    fun checkAndSaveLoginInfo(loginInfoDTO: LoginInfoDTO): Boolean
    {
        try {
            if(!mPaymentApplicationHelper.existsUserByUserName(loginInfoDTO.userName))
                return false

            val loginInfo = mLoginInfoMapper.toLoginInfo(loginInfoDTO)

            if(mPaymentApplicationHelper.existsUserByUserNameAndPassword(loginInfoDTO.userName,loginInfoDTO.password))
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
}