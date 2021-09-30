package com.example.vaccnow.service;

import java.util.List;

import com.example.vaccnow.entity.Vaccine;

public interface VaccineService extends BaseService<Vaccine, Integer> {

    List<Vaccine> getVaccineModelByBranchId(Integer branchId);

}
