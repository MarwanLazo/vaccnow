package com.example.vaccnow.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_branch")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id", "name", "email" }, callSuper = false)
public class Branch extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Integer id;

    @Column(name = "branch_name")
    private String name;

    @Column(name = "branch_location")
    private String location;

    @Column(name = "branch_phone")
    private String phone;

    @Column(name = "branch_email")
    private String email;

    @Column(name = "branch_work_start_date")
    private Date workStartDate;

    @Column(name = "branch_work_end_date")
    private Date workEndDate;

    @Column(name = "branch_address")
    private String address;

    @Transient
    private boolean vaccineId;

    @Override
    public Integer getPK() {
        return getId();
    }

    public Branch(Integer id) {
        this.id = id;
    }

    @PrePersist
    private void doPrePersist() {
        if (getAddress() == null)
            setAddress("Lazo Address");
    }

}