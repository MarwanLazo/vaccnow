package com.example.vaccnow.repository;

import java.util.Optional;

import com.example.vaccnow.entity.VaccineBranch;
import com.example.vaccnow.entity.VaccineBranchPK;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineBranchRepository extends BaseRepository<VaccineBranch, VaccineBranchPK> {

    @Query("SELECT vb FROM VaccineBranch vb WHERE vb.id.branchId.id=?1 AND vb.id.vaccine.id=?2")
    Optional<VaccineBranch> findById(Integer branchId, Integer vaccineId);

}
