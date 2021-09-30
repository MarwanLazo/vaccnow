package com.example.vaccnow.controller;

import java.util.List;

import com.example.vaccnow.model.VaccineModel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface VaccineController extends BaseController<VaccineModel, Integer> {

    @GetMapping({ "/{branchId}" })
    ResponseEntity<List<VaccineModel>> getVaccineByBranchId(@PathVariable Integer branchId);

}
