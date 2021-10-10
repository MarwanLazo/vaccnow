package com.example.vaccnow.service;

import java.io.Serializable;
import java.util.List;

import com.example.vaccnow.entity.BaseEntity;

public interface BaseService<EN extends BaseEntity<? extends Serializable>, PK extends Serializable> {

    List<EN> findAll();

    EN findById(PK id);

    EN create(EN en);

    EN update(EN en);

    void deleteAll();

    void deleteById(PK id);
}
