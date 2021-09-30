package com.example.vaccnow.entity;

import java.io.Serializable;

import javax.persistence.OneToOne;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class VaccineBranchPK implements Serializable {

    @OneToOne
    @JoinColumn(referencedColumnName = "vaccine_id", name = "vaccine_id")
    private Vaccine vaccine;

    @OneToOne
    @JoinColumn(referencedColumnName = "branch_id", name = "branch_id")
    private Branch branchId;
}
