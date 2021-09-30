package com.example.vaccnow.repository;

import java.io.Serializable;

import com.example.vaccnow.entity.BaseEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<EN extends BaseEntity<? extends Serializable>, PK extends Serializable>
        extends JpaRepository<EN, PK>, JpaSpecificationExecutor<EN> {

}
