package com.example.vaccnow.repository;

import com.example.vaccnow.entity.Branch;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends BaseRepository<Branch, Integer> {

}
