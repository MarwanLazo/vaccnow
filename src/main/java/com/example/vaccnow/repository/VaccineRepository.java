package com.example.vaccnow.repository;

import com.example.vaccnow.entity.Vaccine;

import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends BaseRepository<Vaccine, Integer> {

}
