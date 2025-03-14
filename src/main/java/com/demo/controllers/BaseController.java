package com.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.entities.BaseEntity;
import com.demo.helperEntities.ApiResponseHelperEntity;
import com.demo.impl.BaseServiceImpl;
import com.demo.services.BaseControllerService;
import com.demo.utils.ConstantsUtils;

public abstract class BaseController <E extends BaseEntity, S extends BaseServiceImpl<E, Long>> implements BaseControllerService<E, Long> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	protected S servicio;
	
	@GetMapping
	public ResponseEntity<ApiResponseHelperEntity> finAll() {
		LOGGER.debug("init finAll");
		
		Optional<List<E>> listDatosOptional = this.servicio.findAll();
		if (listDatosOptional.isPresent() && !listDatosOptional.get().isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(ApiResponseHelperEntity.success(HttpStatus.OK.value(), ConstantsUtils.DATA_LIST_OK, listDatosOptional.get()));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseHelperEntity.error(HttpStatus.NOT_FOUND.value(), ConstantsUtils.DATA_NOT_FOUND, null, null));
		}
	}
	
	@GetMapping("/page")
	public ResponseEntity<ApiResponseHelperEntity> findAll(Pageable pageable) {	
		LOGGER.debug("init findAll");		
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponseHelperEntity.success(HttpStatus.OK.value(), ConstantsUtils.DATA_LIST_OK, servicio.findAll(pageable).get()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseHelperEntity> findById(@PathVariable Long id) {
		LOGGER.debug("init findById");
		
		Optional<E> entity = servicio.findById(id);
		if (entity.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(ApiResponseHelperEntity.success(HttpStatus.OK.value(), ConstantsUtils.DATA_LIST_OK, entity.get()));
		} 
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseHelperEntity.error(HttpStatus.NOT_FOUND.value(), ConstantsUtils.DATA_NOT_FOUND, null, null));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponseHelperEntity> save(@RequestBody E entity) {	
		LOGGER.debug("init save");		
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseHelperEntity.success(HttpStatus.CREATED.value(), ConstantsUtils.DATA_SAVE_OK, servicio.save(entity).get()));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseHelperEntity> update(@PathVariable Long id, @RequestBody E entity) {	
		LOGGER.debug("init update");
		
		Optional<E> Eentity = servicio.update(id, entity);
		if (Eentity.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(ApiResponseHelperEntity.success(HttpStatus.OK.value(), ConstantsUtils.DATA_UPDATE_OK, Eentity.get()));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseHelperEntity.error(HttpStatus.NOT_FOUND.value(), ConstantsUtils.DATA_NOT_FOUND, null, null));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseHelperEntity> delete(@PathVariable Long id) {
		LOGGER.debug("init delete");
		
		Boolean idDelete = servicio.delete(id);
		if (idDelete) {
			return ResponseEntity.status(HttpStatus.OK).body(ApiResponseHelperEntity.success(HttpStatus.OK.value(), ConstantsUtils.DATA_DELETE_OK, id));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseHelperEntity.error(HttpStatus.NOT_FOUND.value(), ConstantsUtils.DATA_NOT_FOUND, null, null));
		}
	}
}
