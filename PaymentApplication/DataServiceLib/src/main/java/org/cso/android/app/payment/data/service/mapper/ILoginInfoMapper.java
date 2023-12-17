package org.cso.android.app.payment.data.service.mapper;

import org.cso.android.app.payment.data.service.dto.LoginInfoSaveDTO;
import org.cso.android.app.payment.data.service.dto.LoginInfoDTO;
import org.cso.android.app.payment.repository.entity.LoginInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "LoginInfoMapperImpl")
public interface ILoginInfoMapper{
    LoginInfo toLoginInfo(LoginInfoSaveDTO loginInfoDTO);

    @Mapping(source = "loginDateTime", target = "loginDateTimeStr", dateFormat = "dd/MM/yyyy kk:mm:ss")
    LoginInfoDTO toLoginInfoDTO(LoginInfo loginInfo);

}
