package com.example.vaccnow.service;

import java.io.Serializable;
import java.util.List;

import com.example.vaccnow.entity.BaseEntity;
import com.example.vaccnow.repository.BaseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BaseServiceImpl<EN extends BaseEntity<? extends Serializable>, PK extends Serializable, JPA extends BaseRepository<EN, PK>>
        implements BaseService<EN, PK> {

    protected final JPA repo;

    @Override
    public List<EN> findAll() {
        List<EN> eNs = repo.findAll();
        log.info("Loaded No. Entities method 'findAll()' ({}) ", eNs.size());
        return eNs;
    }

    @Override
    public EN create(EN en) {
        en = repo.save(en);
        log.info("Create   Entity  '({})'  with Id : ({}) ", en.getClass(), en.getPK());
        return en;
    }

    @Override
    public void deleteAll() {
        log.info("Delete All  Entities ");
        repo.deleteAll();
    }

    @Override
    public EN update(EN en) {
        en = repo.save(en);
        log.info("Update Entity  '({})'  with Id : ({}) ", en.getClass(), en.getPK());
        return en;
    }

    @Override
    public EN findById(PK id) {
        EN en = repo.findById(id).orElse(null);
        log.info("Load Entity  '({})'  with Id : ({}) ", id, en);
        return en;
    }

    @Override
    public void deleteById(PK id) {
        repo.findById(id).ifPresent(en -> {
            log.warn("Delete Entity  '({})'  with Id : ({}) ", en.getClass(), id);
            repo.delete(en);
        });
    }

}
