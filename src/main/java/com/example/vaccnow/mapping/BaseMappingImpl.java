package com.example.vaccnow.mapping;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.example.vaccnow.entity.BaseEntity;
import com.example.vaccnow.model.BaseModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseMappingImpl<EN extends BaseEntity<? extends Serializable>, Model extends BaseModel>
		implements BaseMapping<EN, Model> {

	private final ModelMapper mapper;
	private final Class<EN> enClazz;
	private final Class<Model> modelClazz;

	@Override
	public EN mapToEntity(Model model) {
		return mapper.map(model, enClazz);
	}

	@Override
	public Model mapToModel(EN en) {
		return mapper.map(en, modelClazz);
	}

	@Override
	public List<Model> mapToModel(List<EN> entity) {
		if (entity.isEmpty())
			return Collections.emptyList();
		return entity.stream().map(this::mapToModel).collect(Collectors.toList());
	}

	@Override
	public List<EN> mapToEntity(List<Model> model) {
		if (model.isEmpty())
			return Collections.emptyList();
		return model.stream().map(this::mapToEntity).collect(Collectors.toList());
	}

	@Override
	public void mapToEntity(Model model, EN en) {
		mapper.map(model, en);
	}

	@Override
	public void mapToModel(EN en, Model model) {
		mapper.map(en, model);
	}

	@Override
	public void map(Model source, Model target) {
		mapper.map(source, target);
	}

	@Override
	public void map(EN source, EN target) {
		mapper.map(source, target);
	}

}
