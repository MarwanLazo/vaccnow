package com.example.vaccnow.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import com.example.vaccnow.entity.Branch;
import com.example.vaccnow.mapping.BranchMapping;
import com.example.vaccnow.model.BranchModel;
import com.example.vaccnow.service.BranchService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "branch" })
public class BranchControllerImpl extends BaseControllerImpl<BranchModel, Integer, Branch, BranchService, BranchMapping>
        implements BranchController {

    public BranchControllerImpl(BranchService service, BranchMapping mapper) {
        super(service, mapper);
    }

    public ResponseEntity<String> greeting() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<BranchModel>> branchVaccineAvailablityByVaccineId(Integer vaccineId) {
        List<Branch> branches = service.branchVaccineAvailablityByVaccineId(vaccineId);
        List<BranchModel> branchVaccineAvailablity = mapper.mapToModel(branches);
        return new ResponseEntity<>(branchVaccineAvailablity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Date>> getAvailableTimeByBranch(Integer id) {
        Branch en = service.findById(id);
        Map<String, Date> workMap = Map.of("from", en.getWorkStartDate(), "to", en.getWorkEndDate());
        return new ResponseEntity<>(workMap, HttpStatus.OK);
    }

}
