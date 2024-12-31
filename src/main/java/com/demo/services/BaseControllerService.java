package com.demo.services;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.entities.BaseEntity;
import com.demo.helperEntities.ApiResponseHelperEntity;

public interface BaseControllerService <E extends BaseEntity, ID extends Serializable> {

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseEntity<ApiResponseHelperEntity> findAll();
	
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseEntity<ApiResponseHelperEntity> findAll(Pageable pageable);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseEntity<ApiResponseHelperEntity> findById(@PathVariable ID id);
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseEntity<ApiResponseHelperEntity> save(@RequestBody E entity);
	
	/**
	 * 
	 * @param id
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseEntity<ApiResponseHelperEntity> update(@PathVariable ID id, @RequestBody E entity);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseEntity<ApiResponseHelperEntity> delete(@PathVariable ID id);
}
