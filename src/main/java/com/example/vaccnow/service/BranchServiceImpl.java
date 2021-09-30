package com.example.vaccnow.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.vaccnow.entity.Branch;
import com.example.vaccnow.repository.BranchRepository;

import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class BranchServiceImpl extends BaseServiceImpl<Branch, Integer, BranchRepository> implements BranchService {

    private final VaccineBranchService vaccineBranchService;

    public BranchServiceImpl(BranchRepository repo, VaccineBranchService vaccineBranchService) {
        super(repo);
        this.vaccineBranchService = vaccineBranchService;
    }

    @Override
    public List<Branch> branchVaccineAvailablityByVaccineId(Integer vaccineId) {
        List<Branch> branchByvaccineId = vaccineBranchService.branchVaccineAvailablityByVaccineId(vaccineId);
        List<@NonNull Integer> branchIdsByvaccineId = branchByvaccineId.stream().map(Branch::getId).distinct()
                .collect(Collectors.toList());
        List<Branch> branch = repo.findAll();

        branch = branch.stream().map(br -> {
            if (branchIdsByvaccineId.contains(br.getId()))
                br.setVaccineId(true);
            return br;
        }).collect(Collectors.toList());

        return branch;
    }

}