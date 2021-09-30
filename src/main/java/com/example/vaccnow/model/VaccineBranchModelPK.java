package com.example.vaccnow.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class VaccineBranchModelPK implements Serializable {

    private VaccineModel vaccine;

    private BranchModel branchId;
}
