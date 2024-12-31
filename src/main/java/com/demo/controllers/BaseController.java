package com.demo.controllers;

import java.util.List;
import java.util.Optional;
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

public abstract class BaseController <E extends BaseEntity, S extends BaseServiceImpl<E, Long>> implements BaseControllerService<E, Long> {

	@Autowired
	protected S servicio;
	
	@GetMapping
	public ResponseEntity<ApiResponseHelperEntity> finAll() {		
		try {
			Optional<List<E>> listDatosOptional = this.servicio.findAll();
			if (listDatosOptional.isPresent() && !listDatosOptional.get().isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(ApiResponseHelperEntity.success(HttpStatus.OK.value(),
						"Datos listados con éxito.", listDatosOptional.get()));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(ApiResponseHelperEntity.error(HttpStatus.NOT_FOUND.value(), "No se han encontrado datos.", null, null));
			}
		} catch (Exception e) {
			return responseInternalServerError(e);
		}
	}
	
	@GetMapping("/page")
	public ResponseEntity<ApiResponseHelperEntity> findAll(Pageable pageable) {
		try {						
			return ResponseEntity.status(HttpStatus.OK).body(ApiResponseHelperEntity.success(HttpStatus.OK.value(), 
					"Datos listados con éxito.", servicio.findAll(pageable).get()));
		} catch (Exception e) {
			return responseInternalServerError(e);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseHelperEntity> findById(@PathVariable Long id) {
		try {
			Optional<E> entity = servicio.findById(id);
			if (entity.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(ApiResponseHelperEntity.success(HttpStatus.OK.value(), "Dato listado con éxito!", entity.get()));
			} 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseHelperEntity.error(HttpStatus.NOT_FOUND.value(), "Registro no encontrado!", null, null));

		} catch (Exception e) {
			return responseInternalServerError(e);
		}
	}
	
	@PostMapping
	public ResponseEntity<ApiResponseHelperEntity> save (@RequestBody E entity) {		
		try {			
			return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseHelperEntity.success(HttpStatus.CREATED.value(), "Dato guardado con éxito!", servicio.save(entity).get()));
		} catch (Exception e) {
			return responseInternalServerError(e);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseHelperEntity> update(@PathVariable Long id, @RequestBody E entity) {		
		try {
			
			Optional<E> Eentity = servicio.update(id, entity);
			if (Eentity.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(ApiResponseHelperEntity.success(HttpStatus.OK.value(), "Dato actualizado con éxito!", Eentity.get()));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseHelperEntity.error(HttpStatus.NOT_FOUND.value(),  "Registro no encontrado!", null, null));
			
		} catch (Exception e) {
			return responseInternalServerError(e);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseHelperEntity> delete(@PathVariable Long id) {
		try {
			Boolean idDelete = servicio.delete(id);
			if (idDelete) {
				return ResponseEntity.status(HttpStatus.OK).body(ApiResponseHelperEntity.success(HttpStatus.OK.value(), "Registro eliminado con éxito!", id));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseHelperEntity.error(HttpStatus.NOT_FOUND.value(), "Registro no encontrado!", null, null));
			}
		} catch (Exception e) {
			return responseInternalServerError(e);
		}
	}
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	private static ResponseEntity<ApiResponseHelperEntity> responseInternalServerError(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiResponseHelperEntity.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
						"Error en el servidor. Por favor, inténtelo más tarde. Detalles: " + e.getMessage(), null, null));
	}
}
