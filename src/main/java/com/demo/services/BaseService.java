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
	 * @throws Exception
	 */
	public Optional<List<E>> findAll() throws Exception;
	
	/**
	 * 
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public Optional<Page<E>> findAll(Pageable pageable) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Optional<E> findById(ID id) throws Exception;
	
	/**
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Optional<E> save(E entity) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Optional<E> update(ID id, E entity) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Boolean delete(ID id) throws Exception;
	
}
