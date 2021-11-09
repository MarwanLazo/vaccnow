package com.example.vaccnow.controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import com.example.vaccnow.entity.BaseEntity;
import com.example.vaccnow.mapping.BaseMapping;
import com.example.vaccnow.model.BaseModel;
import com.example.vaccnow.service.BaseService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
public abstract class BaseControllerImpl<Model extends BaseModel, PK extends Serializable, EN extends BaseEntity<PK>, Service extends BaseService<EN, PK>, Mapper extends BaseMapping<EN, Model>>
        implements BaseController<Model, PK> {

    protected final Service service;
    protected final Mapper mapper;

    @Override
    public ResponseEntity<List<Model>> findAll() {
        List<EN> eNs = service.findAll();
        List<Model> models = mapper.mapToModel(eNs);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Model> create(@Valid Model model) {
        doBeforeCreate(model);
        EN en = mapper.mapToEntity(model);
        en = service.create(en);
        model = mapper.mapToModel(en);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Model> update(@Valid Model model) {
        doBeforeUpdate(model);

        EN en = mapper.mapToEntity(model);

        EN saved = service.findById(en.getPK());

        mapper.map(en, saved);

        en = service.update(saved);

        model = mapper.mapToModel(en);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    protected void doBeforeCreate(Model model) {
    }

    protected void doBeforeUpdate(Model model) {
    }

    @Override
    public ResponseEntity<Void> delete(PK id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Model> findById(PK id) {
        EN en = service.findById(id);
        Model model = mapper.mapToModel(en);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

}
