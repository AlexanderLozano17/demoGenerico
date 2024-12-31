package com.demo.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.entities.BaseEntity;

/**
 * 
 * @param <E>
 * @param <ID>
 */
public interface BaseService <E extends BaseEntity, ID extends Serializable> {

	/**
	 * 
	 * @return
	 */
	public Optional<List<E>> findAll();
	
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	public Optional<Page<E>> findAll(Pageable pageable);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Optional<E> findById(ID id);
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public Optional<E> save(E entity);
	
	/**
	 * 
	 * @param id
	 * @param entity
	 * @return
	 */
	public Optional<E> update(ID id, E entity);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Boolean delete(ID id);
	
}
