package com.example.vaccnow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaccineModel implements BaseModel {

    private Integer id;

    private String name;

    private String manufacture;

    private String country;

    private String notes;

}
