package org.cso.android.app.data.service.mapper

import org.cso.android.app.data.service.dto.PaymentSaveDTO
import org.cso.android.app.payment.repository.entity.Payment
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(implementationName = "PaymentMapperImpl")
interface IPaymentMapper {

    @Mapping(source = "price", target = "unitPrice")
    @Mapping(source = "desc", target = "description")
    fun toPayment(paymentSaveDTO: PaymentSaveDTO): Payment



    fun toPaymentSaveDTO(payment: Payment): PaymentSaveDTO
}