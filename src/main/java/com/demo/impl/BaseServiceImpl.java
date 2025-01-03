package com.demo.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entities.BaseEntity;
import com.demo.repositories.BaseRepository;
import com.demo.services.BaseService;

/**
 * Implementación abstracta para el servicio base que gestiona operaciones comunes de 
 * CRUD y eliminación lógica para entidades.
 *
 * @param <E> Tipo de entidad que extiende BaseEntity.
 * @param <ID> Tipo de identificador de la entidad.
 */
public abstract class BaseServiceImpl <E extends BaseEntity, ID extends Serializable> implements BaseService<E, ID> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);
	
	protected BaseRepository<E, ID> baseRepository;
	
	public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
		super();
		this.baseRepository = baseRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<List<E>> findAll() {
		LOGGER.debug("init findAll");
		return Optional.ofNullable(this.baseRepository.findAll());	
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Page<E>> findAll(Pageable pageable) {
		LOGGER.debug("init findAll");
		return Optional.ofNullable(this.baseRepository.findAll(pageable));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<List<E>> findActiveRecords() {
		LOGGER.debug("init findActiveRecords");
		
		return Optional.of(this.baseRepository.findAll().stream()
				.filter(entity -> Boolean.FALSE.equals(entity.getIsDeleted()))
				.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<List<E>> findInactiveRecords() {
		LOGGER.debug("init findInactiveRecords");
		
		return Optional.of(this.baseRepository.findAll().stream()
				.filter(entity -> Boolean.TRUE.equals(true))
				.toList());		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<E> findInactiveRecordId(ID id) {
		LOGGER.debug("init findInactiveRecordId");
		
		Optional<E> entityOptional = this.baseRepository.findById(id);
		if (entityOptional.isPresent()) {
			E entity = entityOptional.get();
			if (entity.getIsDeleted()) {
				return Optional.of(entity);
			}
		}
		return Optional.empty();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<E> findById(ID id) {
		LOGGER.debug("init findById");
		return this.baseRepository.findById(id);	
	}

	@Override
	@Transactional
	public Optional<E> save(E entity) {
		LOGGER.debug("init save");
		return Optional.ofNullable(this.baseRepository.save(entity));	
	}

	@Override
	@Transactional
	public Optional<E> update(ID id, E entity) {	
		LOGGER.debug("init update");
		
		Optional<E> entityOptional = this.baseRepository.findById(id);
		if (entityOptional.isPresent()) {
			E entityUpdate = entityOptional.get();
			entityUpdate = this.baseRepository.save(entity);
			return Optional.ofNullable(entityUpdate);
		}
		return Optional.empty();
	}

	@Override
	@Transactional
	public Boolean delete(ID id) {
		LOGGER.debug("init delete");
		
		if (this.baseRepository.existsById(id)) {
			this.baseRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	@Override
	@Transactional
	public Boolean softDelete(ID id) {
		LOGGER.debug("init softDelete");
		
		Optional<E> entityOptional = baseRepository.findById(id);
		if (entityOptional.isPresent()) {			
			E entity = entityOptional.get();
			entity.onRemove();
			baseRepository.save(entity);			
			return true;
		}
		return false;
	}
}