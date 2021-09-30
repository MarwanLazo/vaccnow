package com.example.vaccnow.service;

import java.util.List;

import com.example.vaccnow.entity.Branch;

public interface BranchService extends BaseService<Branch, Integer> {

    List<Branch> branchVaccineAvailablityByVaccineId(Integer vaccineId);

}
