package com.demo.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.demo.entities.BaseEntity;

@NoRepositoryBean
public interface BaseRepository <E extends BaseEntity,  ID extends Serializable> extends JpaRepository<E, ID> {

}
