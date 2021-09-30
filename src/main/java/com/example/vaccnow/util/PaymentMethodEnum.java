package com.example.vaccnow.util;

import java.util.stream.Stream;

import lombok.Getter;

@Getter
public enum PaymentMethodEnum {
    CASH(1), CREDIT(2), FAWRY(3);

    private Integer intValue;

    private PaymentMethodEnum(Integer intValue) {
        this.intValue = intValue;
    }

    public static Integer getIntValueByPaymentMethodEnum(PaymentMethodEnum attribute) {
        if (attribute == null)
            return null;
        return Stream.of(PaymentMethodEnum.values()).filter(val -> val.equals(attribute))
                .map(PaymentMethodEnum::getIntValue).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(attribute + " not Such Enum value."));

    }

    public static PaymentMethodEnum getPaymentMethodEnumByIntValue(Integer intValue) {
        if (intValue == null)
            return null;
        return Stream.of(PaymentMethodEnum.values()).filter(val -> val.getIntValue().equals(intValue)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(intValue + " not Such Enum Id found."));
    }
}