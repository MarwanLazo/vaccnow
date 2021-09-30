package com.example.vaccnow.controller;

import java.security.Provider.Service;
import java.util.List;

import com.example.vaccnow.entity.Vaccine;
import com.example.vaccnow.mapping.VaccineMapping;
import com.example.vaccnow.model.VaccineModel;
import com.example.vaccnow.service.VaccineService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "vaccine" })
public class VaccineControllerImpl
        extends BaseControllerImpl<VaccineModel, Integer, Vaccine, VaccineService, VaccineMapping>
        implements VaccineController {

    public VaccineControllerImpl(VaccineService service, VaccineMapping mapper) {
        super(service, mapper);
    }

    @Override
    public ResponseEntity<List<VaccineModel>> getVaccineByBranchId(Integer branchId) {
        List<Vaccine> vaccines = service.getVaccineModelByBranchId(branchId);
        List<VaccineModel> models = mapper.mapToModel(vaccines);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

}
