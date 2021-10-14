package com.example.vaccnow.controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import com.example.vaccnow.model.BaseModel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface BaseController<Model extends BaseModel, PK extends Serializable> {

    @GetMapping
    ResponseEntity<List<Model>> findAll();

    @PostMapping
    ResponseEntity<Model> create(@Valid @RequestBody Model model);

    @PutMapping
    ResponseEntity<Model> update(@RequestBody @Valid Model model);

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable PK id);

    @GetMapping("{id}")
    ResponseEntity<Model> findById(@PathVariable PK id);

}
