package com.demo.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.entities.BaseEntity;
import com.demo.helperEntities.ApiResponseHelperEntity;

/**
 * Interfaz general para los servicios de los controladores, proporcionado 
 * métodos comunes para el manejo de entidades en la aplicación.
 *
 * @param <E> Tipo de entidad que extiende BaseEntity.
 * @param <ID> Tipo de identificador de la entidad.
 */
public interface BaseControllerService <E extends BaseEntity, ID extends Serializable> {

	/**
	 * Recupera todas las entidades, sin ningún filtro.
	 * 
	 * @return un ResponseEntity con un objeto ApiResponseHelperEntity que 
	 * contiene los resultados de la consulta.
	 */
	@SuppressWarnings("rawtypes")
	ResponseEntity<ApiResponseHelperEntity> findAll();
	
	/**
	 * Recupera todas las entidades con paginación.
	 * 
	 * @param pageable objeto que contiene los parámetros de paginación.
	 * @return un ResponseEntity con un objeto ApiResponseHelperEntity que 
	 * contiene los resultados de la consulta paginada.
	 */
	@SuppressWarnings("rawtypes")
	ResponseEntity<ApiResponseHelperEntity> findAll(Pageable pageable);
	
	/**
	 * Recupera todas las entidades activas (no eliminadas).
	 * 
	 * @return un ResponseEntity con un objeto ApiResponseHelperEntity que 
	 * contiene las entidades activas.
	 */
	@SuppressWarnings("rawtypes")
	ResponseEntity<ApiResponseHelperEntity> findActiveRecords();
	
	/**
	 * Encuentra todas las entidades que no están activas (es decir, aquellas 
	 * que han sido eliminadas lógicamente).
	 * Este método recupera todas las entidades que tienen el campo `isDeleted` 
	 * como `true`, indicando que han sido marcadas como eliminadas.
	 * 
	 * @return un Optional que contiene una lista de entidades inactivas si existen.
	 */
	ResponseEntity<ApiResponseHelperEntity> findInactiveRecords();

	/**
	 * Encuentra una entidad inactiva por su identificador.
	 * Este método busca una entidad que está marcada como eliminada lógicamente 
	 * (es decir, `isDeleted = true`), pero que se encuentra almacenada en la base de datos.
	 * 
	 * @param id el identificador de la entidad que se quiere recuperar.
	 * @return un Optional que contiene la entidad inactiva si se encuentra, o vacío si no se encuentra.
	 */
	ResponseEntity<ApiResponseHelperEntity> findInactiveRecordId(@PathVariable ID id);
	
	/**
	 * Recupera una entidad por su identificador.
	 * 
	 * @param id identificador de la entidad que se desea recuperar.
	 * @return un ResponseEntity con un objeto ApiResponseHelperEntity que 
	 * contiene la entidad encontrada.
	 */
	@SuppressWarnings("rawtypes")
	ResponseEntity<ApiResponseHelperEntity> findById(@PathVariable ID id);
	
	/**
	 * Guarda una nueva entidad.
	 * 
	 * @param entity la entidad que se desea guardar.
	 * @return un ResponseEntity con un objeto ApiResponseHelperEntity que 
	 * contiene la entidad guardada.
	 */
	@SuppressWarnings("rawtypes")
	ResponseEntity<ApiResponseHelperEntity> save(@RequestBody E entity);
	
	/**
	 * Actualiza una entidad existente.
	 * 
	 * @param id el identificador de la entidad que se desea actualizar.
	 * @param entity la entidad con los nuevos datos.
	 * @return un ResponseEntity con un objeto ApiResponseHelperEntity que 
	 * contiene la entidad actualizada.
	 */
	@SuppressWarnings("rawtypes")
	ResponseEntity<ApiResponseHelperEntity> update(@PathVariable ID id, @RequestBody E entity);
	
	/**
	 * Elimina una entidad por su identificador.
	 * 
	 * @param id el identificador de la entidad que se desea eliminar.
	 * @return un ResponseEntity con un objeto ApiResponseHelperEntity que 
	 * indica el resultado de la operación de eliminación.
	 */
	@SuppressWarnings("rawtypes")
	ResponseEntity<ApiResponseHelperEntity> delete(@PathVariable ID id);
	
	/**
	 * Realiza una eliminación lógica de una entidad, marcándola como eliminada 
	 * (por ejemplo, con un campo `isDeleted = true`).
	 * 
	 * @param id el identificador de la entidad que se desea eliminar lógicamente.
	 * @return un ResponseEntity con un objeto ApiResponseHelperEntity que 
	 * indica el resultado de la operación de eliminación lógica.
	 */
	@SuppressWarnings("rawtypes")
	ResponseEntity<ApiResponseHelperEntity> softDelete(@PathVariable ID id);
}
