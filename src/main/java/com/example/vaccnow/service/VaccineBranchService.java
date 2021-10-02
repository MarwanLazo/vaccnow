package com.example.vaccnow.service;

import java.util.List;

import com.example.vaccnow.entity.Branch;
import com.example.vaccnow.entity.Vaccine;
import com.example.vaccnow.entity.VaccineBranch;
import com.example.vaccnow.entity.VaccineBranchPK;

public interface VaccineBranchService extends BaseService<VaccineBranch, VaccineBranchPK> {

    List<Vaccine> getVaccineModelByBranchId(Integer branchId);

    List<Branch> branchVaccineAvailablityByVaccineId(Integer vaccineId);

    VaccineBranch saveVaccineBranch(Integer branchId, Integer vaccineId);

}
