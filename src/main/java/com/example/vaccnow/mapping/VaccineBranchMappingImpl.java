package com.example.vaccnow.mapping;

import com.example.vaccnow.entity.VaccineBranch;
import com.example.vaccnow.model.VaccineBranchModel;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class VaccineBranchMappingImpl extends BaseMappingImpl<VaccineBranch, VaccineBranchModel>
        implements VaccineBranchMapping {

    public VaccineBranchMappingImpl(ModelMapper mapper) {
        super(mapper, VaccineBranch.class, VaccineBranchModel.class);
    }
}