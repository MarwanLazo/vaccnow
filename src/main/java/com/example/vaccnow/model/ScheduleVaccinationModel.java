package com.example.vaccnow.model;

import java.util.Date;

import com.example.vaccnow.util.PaymentMethodEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleVaccinationModel implements BaseModel {
    private Integer id;

    private PaymentMethodEnum paymentMethod;

    private String request;

    private String vacDesc;

    private Date vacTime;

    private BranchModel branch;

    private boolean confirmed;

}
