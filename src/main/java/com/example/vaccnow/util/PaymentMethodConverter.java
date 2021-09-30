package com.example.vaccnow.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PaymentMethodConverter implements AttributeConverter<PaymentMethodEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PaymentMethodEnum attribute) {
        return PaymentMethodEnum.getIntValueByPaymentMethodEnum(attribute);
    }

    @Override
    public PaymentMethodEnum convertToEntityAttribute(Integer intValue) {
        return PaymentMethodEnum.getPaymentMethodEnumByIntValue(intValue);
    }

}
