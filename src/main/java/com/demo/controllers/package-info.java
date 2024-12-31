/**
 * Este paquete contiene los controladores REST de la aplicación.
 * 
 * Los controladores son responsables de manejar las solicitudes HTTP, interactuar con los servicios
 * de negocio y devolver respuestas adecuadas al cliente.
 * 
 * Principales convenciones utilizadas en este paquete:
 * - Todas las rutas comienzan con "/api".
 * - Las respuestas devuelven datos en formato JSON.
 * - Las clases están anotadas con @RestController para habilitar el manejo REST.
 * 
 * Controladores incluidos:
 * - BaseController: Gestión de recursos de los métodos genericos 
 * 
 * @since 1.0
 */

@NonNullApi
@NonNullFields
package com.demo.controllers;

import org.springframework.lang.NonNullApi;
import org.springframework.lang.NonNullFields;
