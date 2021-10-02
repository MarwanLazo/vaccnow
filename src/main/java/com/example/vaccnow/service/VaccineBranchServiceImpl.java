package com.example.vaccnow.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.vaccnow.entity.Branch;
import com.example.vaccnow.entity.Vaccine;
import com.example.vaccnow.entity.VaccineBranch;
import com.example.vaccnow.entity.VaccineBranchPK;
import com.example.vaccnow.repository.BranchRepository;
import com.example.vaccnow.repository.VaccineBranchRepository;
import com.example.vaccnow.repository.VaccineRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VaccineBranchServiceImpl extends BaseServiceImpl<VaccineBranch, VaccineBranchPK, VaccineBranchRepository>
                implements VaccineBranchService {

        private VaccineRepository vaccineRepository;
        private BranchRepository branchRepository;

        public VaccineBranchServiceImpl(VaccineBranchRepository repo, VaccineRepository vaccineRepository,
                        BranchRepository branchRepository) {
                super(repo);
                this.vaccineRepository = vaccineRepository;
                this.branchRepository = branchRepository;
        }

        @Override
        public List<Vaccine> getVaccineModelByBranchId(Integer branchId) {

                return repo.findAll((root, query, criteriaBuilder) -> criteriaBuilder
                                .equal(root.get("id").get("branchId").get("id"), branchId)).stream()
                                .map(VaccineBranch::getId).map(VaccineBranchPK::getVaccine)
                                .collect(Collectors.toList());
        }

        @Override
        public List<Branch> branchVaccineAvailablityByVaccineId(Integer vaccineId) {

                return repo.findAll((root, query, criteriaBuilder) -> criteriaBuilder
                                .equal(root.get("id").get("vaccine").get("id"), vaccineId)).stream()
                                .map(VaccineBranch::getId).map(VaccineBranchPK::getBranchId)
                                .collect(Collectors.toList());
        }

        @Override
        @Transactional
        public VaccineBranch saveVaccineBranch(Integer branchId, Integer vaccineId) {
                Optional<Branch> branch = branchRepository.findById(branchId);
                Optional<Vaccine> vaccine = vaccineRepository.findById(vaccineId);
                if (branch.isPresent() && vaccine.isPresent()) {
                        repo.deleteById(VaccineBranchPK.builder().branchId(branch.get()).vaccine(vaccine.get())
                                        .build());
                        return create(VaccineBranch.builder().id(
                                        VaccineBranchPK.builder().branchId(branch.get()).vaccine(vaccine.get()).build())
                                        .build());
                }
                return null;
        }

}
