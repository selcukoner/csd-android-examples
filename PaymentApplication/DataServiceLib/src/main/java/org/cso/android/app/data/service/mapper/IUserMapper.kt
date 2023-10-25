package org.cso.android.app.data.service.mapper

import org.cso.android.app.data.service.dto.UserSaveDTO
import org.cso.android.app.payment.repository.entity.User
import org.mapstruct.Mapper

@Mapper(implementationName = "UserMapperImpl")
interface IUserMapper {
    fun toUser(userSaveDTO: UserSaveDTO): User
    fun toUserSaveDTO(user: User):UserSaveDTO
}