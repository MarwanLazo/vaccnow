package com.example.vaccnow.mapping;

import com.example.vaccnow.entity.Vaccine;
import com.example.vaccnow.model.VaccineModel;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class VaccineMappingImpl extends BaseMappingImpl<Vaccine, VaccineModel> implements VaccineMapping {

    public VaccineMappingImpl(ModelMapper mapper) {
        super(mapper, Vaccine.class, VaccineModel.class);
    }

}
