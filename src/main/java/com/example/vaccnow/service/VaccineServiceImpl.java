package com.example.vaccnow.service;

import java.util.List;

import com.example.vaccnow.entity.Vaccine;
import com.example.vaccnow.repository.VaccineRepository;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VaccineServiceImpl extends BaseServiceImpl<Vaccine, Integer, VaccineRepository> implements VaccineService {
    private final VaccineBranchService vaccineBranchService;

    public VaccineServiceImpl(VaccineRepository repo, VaccineBranchService vaccineBranchService) {
        super(repo);
        this.vaccineBranchService = vaccineBranchService;
    }

    @Override
    public List<Vaccine> getVaccineModelByBranchId(Integer branchId) {
        List<Vaccine> vaccines = vaccineBranchService.getVaccineModelByBranchId(branchId);
        log.info("Vaccines Loaded By BranchId({}) are ({})", branchId, vaccines.size());
        return vaccines;
    }

}
