/**
 * Este paquete contiene las interfaces y clases de repositorios que manejan la persistencia de datos
 * en la base de datos. Las clases dentro de este paquete definen las operaciones CRUD (Crear, Leer, 
 * Actualizar, Eliminar) y consultas personalizadas sobre las entidades del dominio.
 *
 * <p>Las interfaces de repositorio en este paquete están diseñadas para ser utilizadas con tecnologías
 * como Spring Data JPA, Hibernate o cualquier otro marco de persistencia. Estas interfaces proporcionan
 * una abstracción sobre los detalles de la base de datos y facilitan la interacción con las entidades 
 * del sistema.</p>
 *
 * <p>Los repositorios deben cumplir con el principio de separación de preocupaciones, delegando la lógica 
 * de persistencia a este nivel y manteniendo las capas superiores enfocadas en la lógica de negocio.</p>
 *
 * <p>Se recomienda que los métodos de los repositorios sean declarativos y no contengan lógica compleja.</p>
 *
 * @since 1.0
 */

package com.demo.repositories;