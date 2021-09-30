package com.example.vaccnow.controller;

import java.util.List;

import com.example.vaccnow.model.ScheduleVaccinationModel;
import com.example.vaccnow.util.PaymentMethodEnum;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ScheduleVaccinationController extends BaseController<ScheduleVaccinationModel, Integer> {

    @GetMapping({ "/vaccination/{payment}/{email}" })
    ResponseEntity<ScheduleVaccinationModel> scheduleVaccinationByPaymentMethod(
            @PathVariable("payment") PaymentMethodEnum payment, @PathVariable("email") String email);

    @GetMapping({ "/confirmedVaccination/{from}/{to}" })
    ResponseEntity<List<ScheduleVaccinationModel>> getConfirmedVaccinations(@PathVariable("from") Long from,
            @PathVariable("to") Long to);
}
