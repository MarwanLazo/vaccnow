package com.example.vaccnow.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.example.vaccnow.util.ContactNumber;

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

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String location;

    @ContactNumber
    @NotBlank(message = "Phone is mandatory")
    private String phone;

    @Email(message = "Email Frmat Error")
    @NotBlank(message = "Email is mandatory")
    private String email;

    private boolean vaccineId;

    private Date workStartDate;
    private Date workEndDate;

    private Integer workStartHour;
    private Integer workEndHour;

}
