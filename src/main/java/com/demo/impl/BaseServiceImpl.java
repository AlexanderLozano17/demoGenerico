package com.demo.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.entities.BaseEntity;
import com.demo.repositories.BaseRepository;
import com.demo.services.BaseService;

public abstract class BaseServiceImpl <E extends BaseEntity, ID extends Serializable> implements BaseService<E, ID> {
	
	protected BaseRepository<E, ID> baseRepository;
	
	public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
		super();
		this.baseRepository = baseRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<List<E>> findAll() throws Exception {		
		try {
			return Optional.ofNullable(this.baseRepository.findAll());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Page<E>> findAll(Pageable pageable) throws Exception {
		try {
			return Optional.ofNullable(this.baseRepository.findAll(pageable));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}	
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<E> findById(ID id) throws Exception {
		try {
			return this.baseRepository.findById(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}	
	}

	@Override
	@Transactional
	public Optional<E> save(E entity) throws Exception {
		try {
			return Optional.ofNullable(this.baseRepository.save(entity));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}	
	}

	@Override
	@Transactional
	public Optional<E> update(ID id, E entity) throws Exception {
		try {			
			Optional<E> entityOptional = this.baseRepository.findById(id);
			if (this.baseRepository.existsById(id)) {
				E entityUpdate = entityOptional.get();
				entityUpdate = this.baseRepository.save(entity);
				return Optional.ofNullable(entityUpdate);
			}
			return Optional.empty();
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}	
	}

	@Override
	@Transactional
	public Boolean delete(ID id) throws Exception {
		try {
			if (this.baseRepository.existsById(id)) {
				this.baseRepository.deleteById(id);
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}	
	}

}
