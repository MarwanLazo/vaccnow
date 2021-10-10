package com.example.vaccnow.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.vaccnow.entity.Branch;
import com.example.vaccnow.entity.Vaccine;
import com.example.vaccnow.entity.VaccineBranch;
import com.example.vaccnow.entity.VaccineBranchPK;
import com.example.vaccnow.repository.VaccineBranchRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VaccineBranchServiceImpl extends BaseServiceImpl<VaccineBranch, VaccineBranchPK, VaccineBranchRepository>
                implements VaccineBranchService {

        public VaccineBranchServiceImpl(VaccineBranchRepository repo) {
                super(repo);
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

                Optional<VaccineBranch> vaccineBranch = repo.findById(branchId, vaccineId);
                if (vaccineBranch.isPresent()) {
                        repo.delete(vaccineBranch.get());
                        return create(vaccineBranch.get());
                }
                return null;
        }

        @Override
        public VaccineBranch findById(Integer branchId, Integer vaccineId) {
                return repo.findById(branchId, vaccineId).orElse(null);
        }

        @Override
        public void deleteById(Integer branchId, Integer vaccineId) {
                repo.findById(branchId, vaccineId).ifPresent(repo::delete);
        }

}
