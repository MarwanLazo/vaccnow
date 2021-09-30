package com.example.vaccnow.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.vaccnow.model.BranchModel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface BranchController extends BaseController<BranchModel, Integer> {

    @GetMapping("greeting")
    ResponseEntity<String> greeting();

    @GetMapping({ "/{vaccineId}/forBranch" })
    ResponseEntity<List<BranchModel>> branchVaccineAvailablityByVaccineId(@PathVariable("vaccineId") Integer vaccineId);

    @GetMapping({ "/availableTime/{id}" })
    ResponseEntity<Map<String, Date>> getAvailableTimeByBranch(@PathVariable("id") Integer id);

}
