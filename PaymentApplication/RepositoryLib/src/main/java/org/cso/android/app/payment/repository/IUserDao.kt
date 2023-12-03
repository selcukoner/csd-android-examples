package org.cso.android.app.payment.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.karandev.util.data.repository.ICrudRepository
import org.cso.android.app.payment.repository.entity.User

@Dao
interface IUserDao : ICrudRepository<User, String> {

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun findByUserNameAndPassword(username: String, password: String): User?
    fun existsByUserNameAndPassword(username: String, password:String) = findByUserNameAndPassword(username, password ) != null

    @Insert
    fun save(user: User)

    
    fun existById(userName: String): Boolean

}