package com.example.vaccnow.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id", "name", "email" })
@Builder
public class BranchModel implements BaseModel {

    private Integer id;
    private String name;
    private String location;
    private String phone;
    private String email;
    private boolean vaccineId;

    private Date workStartDate;
    private Date workEndDate;

    private Integer workStartHour;
    private Integer workEndHour;

}
