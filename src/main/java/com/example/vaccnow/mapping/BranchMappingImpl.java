package com.example.vaccnow.mapping;

import com.example.vaccnow.entity.Branch;
import com.example.vaccnow.model.BranchModel;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BranchMappingImpl extends BaseMappingImpl<Branch, BranchModel> implements BranchMapping {

    public BranchMappingImpl(ModelMapper mapper) {
        super(mapper, Branch.class, BranchModel.class);
    }

}
