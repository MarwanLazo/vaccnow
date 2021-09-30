package com.example.vaccnow.mapping;

import java.io.Serializable;
import java.util.List;

import com.example.vaccnow.entity.BaseEntity;
import com.example.vaccnow.model.BaseModel;

public interface BaseMapping<EN extends BaseEntity<? extends Serializable>, Model extends BaseModel> {

	EN mapToEntity(Model model);

	void mapToEntity(Model model, EN en);

	Model mapToModel(EN en);

	void mapToModel(EN en, Model model);

	List<Model> mapToModel(List<EN> entity);

	List<EN> mapToEntity(List<Model> model);

	void map(Model source, Model target);

	void map(EN target, EN source);

}
