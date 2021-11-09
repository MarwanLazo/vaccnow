package com.example.vaccnow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_vaccine")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id", "name" }, callSuper = false)
public class Vaccine extends BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id")
    private Integer id;

    @Column(name = "vaccine_name")
    private String name;

    @Column(name = "vaccine_manufacture")
    private String manufacture;

    @Column(name = "vaccine_country")
    private String country;

    @Column(name = "vaccine_notes")
    private String notes;

    @Override
    public Integer getPK() {
        return getId();
    }

    public Vaccine(Integer id) {
        this.id = id;
    }
}
