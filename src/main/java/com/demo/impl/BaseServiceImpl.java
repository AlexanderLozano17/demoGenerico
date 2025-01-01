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
		if (this.baseRepository.existsById(id)) {
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
}