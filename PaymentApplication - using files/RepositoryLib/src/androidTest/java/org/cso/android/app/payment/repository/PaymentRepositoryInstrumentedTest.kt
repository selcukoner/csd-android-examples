package org.cso.android.app.payment.repository

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.cso.android.app.payment.repository.entity.Payment
import org.cso.android.app.payment.repository.entity.User
import org.cso.android.app.payment.repository.global.PAYMENT_FILE
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
class PaymentRepositoryInstrumentedTest {
    companion object{
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val userRepository = UserRepository(appContext)
        val paymentRepository = PaymentRepository(appContext)
    }

    private fun setUpUsers()
    {
        val user1 = User("selcuk","1234","Selçuk", "Oner", LocalDate.of(1,Month.JANUARY, 1), LocalDate.now())
        val user2 = User("cihan","123","Cihan", "Selçuk","Oner", LocalDate.of(1,Month.JANUARY, 1), LocalDate.now())

        userRepository.save(user1)
        userRepository.save(user2)
    }

    private fun setUpPayments()
    {
        val payment1 = Payment(1L, "cihan",26.5, 10.0, "Test1")
        val payment2 = Payment(2L, "selcuk",35.5, 15.0, "Test2")
        val payment3 = Payment(3L, "cihan",43.5, 15.0, "Test3")
        val payment4 = Payment(4L, "uras",33.0, 15.0, "Test4")
        val payment5 = Payment(5L, "uras",34.0, 15.0, "Test5")

        paymentRepository.save(payment1)
        paymentRepository.save(payment2)
        paymentRepository.save(payment3)
        paymentRepository.save(payment4)
        paymentRepository.save(payment5)
    }

    private fun deleteFiles()
    {
        File(appContext.filesDir, USER_FILE).delete()
        File(appContext.filesDir, PAYMENT_FILE).delete()

    }

    @Before
    fun setUp()
    {
        deleteFiles()
        setUpUsers()
        setUpPayments()
}

@Test
    fun save_and_findByUserNameSizeTest()
    {
        assertEquals(2, paymentRepository.findByUserName("uras").size)
    }

}