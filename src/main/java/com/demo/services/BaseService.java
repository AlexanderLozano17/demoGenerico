package com.demo.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.entities.BaseEntity;

/**
 * Interfaz general de servicio para manejar operaciones comunes sobre entidades,
 * como la búsqueda, creación, actualización, eliminación, etc.
 *
 * @param <E> Tipo de la entidad (debe extender BaseEntity).
 * @param <ID> Tipo del identificador de la entidad (generalmente Long o Integer).
 */
public interface BaseService <E extends BaseEntity, ID extends Serializable> {

	/**
	 * Recupera todas las entidades.
	 * Este método retorna todas las entidades almacenadas en la base de datos.
	 * 
	 * @return un Optional que contiene una lista de todas las entidades si existen.
	 */
	Optional<List<E>> findAll();
	
	/**
	 * Recupera todas las entidades con paginación.
	 * Este método permite obtener un subconjunto de las entidades, útil para manejar grandes cantidades de datos.
	 * 
	 * @param pageable objeto de tipo Pageable que determina el tamaño de la página y la página actual.
	 * @return un Optional que contiene una página de entidades.
	 */
	Optional<Page<E>> findAll(Pageable pageable);
	
	/**
     * Encuentra todas las entidades activas (no eliminadas).
     * Este método filtra las entidades activas, excluyendo aquellas que han sido eliminadas lógicamente.
     * 
     * @return un Optional que contiene una lista de entidades activas si existen.
     */
	Optional<List<E>> findActiveRecords();
	
	/**
	 * Encuentra todas las entidades que no están activas (es decir, aquellas que han sido eliminadas lógicamente).
	 * Este método recupera todas las entidades que tienen el campo `isDeleted` como `true`, indicando que han sido marcadas como eliminadas.
	 *
	 * @return un Optional que contiene una lista de entidades inactivas si existen.
	 */
	Optional<List<E>> findInactiveRecords();

	/**
	 * Encuentra una entidad inactiva por su identificador.
	 * Este método busca una entidad que está marcada como eliminada lógicamente (es decir, `isDeleted = true`),
	 * pero que se encuentra almacenada en la base de datos.
	 *
	 * @param id el identificador de la entidad que se quiere recuperar.
	 * @return un Optional que contiene la entidad inactiva si se encuentra, o vacío si no se encuentra.
	 */
	Optional<E> findInactiveRecordId(ID id);

	
	/**
	 * Encuentra una entidad por su identificador.
	 * Este método recupera una entidad específica según su ID.
	 * 
	 * @param id el identificador único de la entidad que se quiere recuperar.
	 * @return un Optional que contiene la entidad si se encuentra o vacío si no.
	 */
	Optional<E> findById(ID id);
	
	/**
	 * Guarda una nueva entidad.
	 * Este método persiste una nueva entidad en la base de datos.
	 * 
	 * @param entity la entidad que se quiere guardar.
	 * @return un Optional que contiene la entidad guardada.
	 */
	Optional<E> save(E entity);
	
	/**
	 * Actualiza una entidad existente.
	 * Este método actualiza una entidad existente en la base de datos.
	 * 
	 * @param id el identificador de la entidad que se quiere actualizar.
	 * @param entity la entidad con los nuevos valores.
	 * @return un Optional que contiene la entidad actualizada.
	 */
	Optional<E> update(ID id, E entity);
	
	/**
	 * Elimina una entidad de forma permanente.
	 * Este método elimina una entidad de la base de datos de forma definitiva.
	 * 
	 * @param id el identificador de la entidad que se quiere eliminar.
	 * @return un Boolean que indica si la eliminación fue exitosa.
	 */
	Boolean delete(ID id);	
	
	/**
	 * Realiza un "soft delete" de una entidad.
	 * Este método realiza una eliminación lógica, marcando la entidad como eliminada sin borrarla físicamente.
	 * 
	 * @param id el identificador de la entidad que se quiere eliminar lógicamente.
	 * @return un Boolean que indica si el "soft delete" fue exitoso.
	 */
	Boolean softDelete(ID id);	
}
