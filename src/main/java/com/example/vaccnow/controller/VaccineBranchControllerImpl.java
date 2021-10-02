package com.example.vaccnow.controller;

import com.example.vaccnow.entity.VaccineBranch;
import com.example.vaccnow.entity.VaccineBranchPK;
import com.example.vaccnow.mapping.VaccineBranchMapping;
import com.example.vaccnow.model.VaccineBranchModel;
import com.example.vaccnow.service.VaccineBranchService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "vaccineBranch" })
public class VaccineBranchControllerImpl extends
        BaseControllerImpl<VaccineBranchModel, VaccineBranchPK, VaccineBranch, VaccineBranchService, VaccineBranchMapping>
        implements VaccineBranchController {

    public VaccineBranchControllerImpl(VaccineBranchService service, VaccineBranchMapping mapper) {
        super(service, mapper);
    }

    @Override
    public ResponseEntity<VaccineBranchModel> saveVaccineBranch(Integer branchId, Integer vaccineId) {
        VaccineBranch en = service.saveVaccineBranch(branchId, vaccineId);
        VaccineBranchModel model = mapper.mapToModel(en);
        return new ResponseEntity<>(model, HttpStatus.OK);

    }

}
