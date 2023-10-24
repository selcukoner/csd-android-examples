package org.cso.android.app.data.service.mapper

import org.cso.android.app.data.service.dto.UserSaveDTO
import org.cso.android.app.payment.repository.entity.User
import java.time.LocalDate
import javax.inject.Inject

class UserMapper @Inject constructor() : IUserMapper {
    override fun toUser(userSaveDTO: UserSaveDTO): User {
        return User(userSaveDTO.userName, userSaveDTO.password, userSaveDTO.firstName, userSaveDTO.middleName, userSaveDTO.lastName, userSaveDTO.birthDate, LocalDate.now())
    }

    override fun toUserSaveDTO(user: User): UserSaveDTO {
        return UserSaveDTO(user.userName, user.password, user.firstName, user.lastName,user.birthDate,user.middleName)
    }
}