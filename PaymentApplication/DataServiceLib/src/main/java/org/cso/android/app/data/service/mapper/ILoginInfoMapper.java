package org.cso.android.app.data.service.mapper;

import org.cso.android.app.data.service.dto.LoginInfoDTO;
import org.cso.android.app.payment.repository.entity.LoginInfo;
import org.mapstruct.Mapper;

@Mapper(implementationName = "LoginInfoMapperImpl")
public interface ILoginInfoMapper{
    LoginInfo toLoginInfo(LoginInfoDTO loginIngoDTO);

}
