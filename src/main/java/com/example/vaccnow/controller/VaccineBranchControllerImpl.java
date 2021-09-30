package com.example.vaccnow.controller;

import com.example.vaccnow.entity.VaccineBranch;
import com.example.vaccnow.entity.VaccineBranchPK;
import com.example.vaccnow.mapping.VaccineBranchMapping;
import com.example.vaccnow.model.VaccineBranchModel;
import com.example.vaccnow.service.VaccineBranchService;

public class VaccineBranchControllerImpl extends
        BaseControllerImpl<VaccineBranchModel, VaccineBranchPK, VaccineBranch, VaccineBranchService, VaccineBranchMapping> {

    public VaccineBranchControllerImpl(VaccineBranchService service, VaccineBranchMapping mapper) {
        super(service, mapper);
    }

}
