package org.cso.android.app.data.service.mapper;

import org.cso.android.app.data.service.dto.LoginInfoDTO;
import org.cso.android.app.data.service.dto.LoginInfoStatusDTO;
import org.cso.android.app.payment.repository.entity.LoginInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.xml.transform.Source;

@Mapper(implementationName = "LoginInfoMapperImpl")
public interface ILoginInfoMapper{
    LoginInfo toLoginInfo(LoginInfoDTO loginInfoDTO);

    @Mapping(source = "loginDateTime", target = "loginDateTimeStr", dateFormat = "dd/MM/yyyy kk:mm:ss")
    LoginInfoStatusDTO toLoginInfoStatusDTO(LoginInfo loginInfo);

}
