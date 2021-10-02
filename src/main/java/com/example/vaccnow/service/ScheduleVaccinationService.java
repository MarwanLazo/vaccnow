package com.example.vaccnow.service;

import java.util.Date;
import java.util.List;

import com.example.vaccnow.entity.ScheduleVaccination;
import com.example.vaccnow.util.PaymentMethodEnum;

public interface ScheduleVaccinationService extends BaseService<ScheduleVaccination, Integer> {

    ScheduleVaccination scheduleVaccinationByPaymentMethod(PaymentMethodEnum payment, String email);

    List<ScheduleVaccination> getConfirmedVaccinations(Date from, Date to);

    List<ScheduleVaccination> getAppliedVaccinationByBranchId(Integer branchId);

}
