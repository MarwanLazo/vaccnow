package com.example.vaccnow.mapping;

import com.example.vaccnow.entity.ScheduleVaccination;
import com.example.vaccnow.model.ScheduleVaccinationModel;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ScheduleVaccinationMappingImpl extends BaseMappingImpl<ScheduleVaccination, ScheduleVaccinationModel>
        implements ScheduleVaccinationMapping {

    public ScheduleVaccinationMappingImpl(ModelMapper mapper) {
        super(mapper, ScheduleVaccination.class, ScheduleVaccinationModel.class);
    }

}
