package com.example.vaccnow.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
@Table(name = "tbl_vaccine_branch", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "vaccine_id", "branch_id" }) })
public class VaccineBranch extends BaseEntity<VaccineBranchPK> {

    @EmbeddedId
    private VaccineBranchPK id;

    @Override
    public VaccineBranchPK getPK() {
        return getId();
    }

}
