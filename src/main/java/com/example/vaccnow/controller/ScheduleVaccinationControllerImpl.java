package com.example.vaccnow.controller;

import java.util.List;

import com.example.vaccnow.entity.ScheduleVaccination;
import com.example.vaccnow.mapping.ScheduleVaccinationMapping;
import com.example.vaccnow.model.ScheduleVaccinationModel;
import com.example.vaccnow.service.ScheduleVaccinationService;
import com.example.vaccnow.util.VaccNowUtils;
import com.example.vaccnow.util.PaymentMethodEnum;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "scheduleVaccination" })
public class ScheduleVaccinationControllerImpl extends
        BaseControllerImpl<ScheduleVaccinationModel, Integer, ScheduleVaccination, ScheduleVaccinationService, ScheduleVaccinationMapping>
        implements ScheduleVaccinationController {

    public ScheduleVaccinationControllerImpl(ScheduleVaccinationService service, ScheduleVaccinationMapping mapper) {
        super(service, mapper);
    }

    @Override
    public ResponseEntity<ScheduleVaccinationModel> scheduleVaccConfirmationByPaymentEmail(PaymentMethodEnum payment,
            String email) {
        ScheduleVaccination en = service.scheduleVaccConfirmationByPaymentEmail(payment, email);
        ScheduleVaccinationModel model = mapper.mapToModel(en);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ScheduleVaccinationModel>> getConfirmedVaccinations(Long from, Long to) {
        List<ScheduleVaccination> eBs = service.getConfirmedVaccinations(VaccNowUtils.getDate(from),
                VaccNowUtils.getDate(to));
        List<ScheduleVaccinationModel> models = mapper.mapToModel(eBs);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ScheduleVaccinationModel>> getAppliedVaccinationByBranchId(Integer branchId) {
        List<ScheduleVaccination> eBs = service.getAppliedVaccinationByBranchId(branchId);
        List<ScheduleVaccinationModel> models = mapper.mapToModel(eBs);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

}
