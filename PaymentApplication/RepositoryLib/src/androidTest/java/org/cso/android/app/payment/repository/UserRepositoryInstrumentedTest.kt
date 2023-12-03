package org.cso.android.app.payment.repository

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.cso.android.app.payment.repository.entity.User
import org.cso.android.app.payment.repository.global.USER_FILE

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.File
import java.time.LocalDate
import java.time.Month

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UserRepositoryInstrumentedTest {
    companion object{
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val userRepository = UserDao(appContext)
    }

    @Before
    fun setUp()
    {
        val file = File(appContext.filesDir, USER_FILE)
        /*if (file.exists() && file.length() > 0L)
            return
        */
        file.delete()
        val user1 = User("selcuk","1234","Selçuk", "Oner", LocalDate.of(1,Month.JANUARY, 1), LocalDate.now())
        val user2 = User("cihan","123","Cihan", "Selçuk","Oner", LocalDate.of(1,Month.JANUARY, 1), LocalDate.now())

        userRepository.save(user1)
        userRepository.save(user2)
    }


    @Test
    fun save_and_findByUserNameAndPasswordSuccessTest() {
        assertNotNull(userRepository.findByUserNameAndPassword("selcuk","1234"))
    }

    @Test
    fun save_and_findByUserNameAndPasswordPasswordFailTest() {
        assertNull(userRepository.findByUserNameAndPassword("cihan","1234"))
    }

    @Test
    fun save_and_findByUserNameAndPasswordUserNameFailTest() {
        assertNull(userRepository.findByUserNameAndPassword("oner","1234"))
    }

    @Test
    fun save_and_findByUserNameAndPasswordBothFailTest() {
        assertNull(userRepository.findByUserNameAndPassword("oner","1234567"))
    }
}