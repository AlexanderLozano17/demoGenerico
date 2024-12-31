package com.demo.config;

/**
 * Esta clase contiene constantes que representan las rutas y nombres de servicio,
 * controlador y repositorio de la API para diferentes versiones (V1, V2).
 * La clase está diseñada para ser usada como una configuración centralizada,
 * evitando la duplicación de cadenas de texto en el código.
 * 
 * Las rutas y nombres están predefinidos para facilitar la gestión y la expansión
 * de la API a medida que se agregan nuevas versiones o funcionalidades.
 * 
 * El constructor privado impide la creación de instancias de esta clase, asegurando
 * que sus miembros sean accesibles solo a través de las constantes definidas.
 */
public final class ConstantesPathApiConfig {
    
    // Constructor privado para evitar instanciación
    private ConstantesPathApiConfig () {
        // Constructor vacío, no permite la creación de instancias
    }

    // Prefijo base para todas las rutas de la API
    private static final String DEMO = "demo_";
    
    /********************************************************************************
     *           RUTAS DE VERSIONES DE LA API
     ********************************************************************************/
    private static final String V1_CONTROLLER = DEMO.concat("api/v1/");
    private static final String V2_CONTROLLER = DEMO.concat("api/v2/");
    
    private static final String V1_SERVICE = DEMO.concat("v1_Service");
    private static final String V2_SERVICE = DEMO.concat("v2_Service");
    
    private static final String V1_REPOSITORY = DEMO.concat("v1_Repository");
    private static final String V2_REPOSITORY = DEMO.concat("v2_Repository");
    
    /********************************************************************************
     *           NOMBRE ÚNICO SERVICIO, CONTROLADOR Y REPOSITORIO PARA LA VERSION 1
     ********************************************************************************/
    
    /**
     * Rutas de controlador para la versión 1 (V1)
     */
    private static final String PATH_CONTROLLER_PERSONAS_V1 = V1_CONTROLLER.concat("personas");
    
    /**
     * Rutas de repositorio para la versión 1 (V1)
     */
    private static final String PATH_REPOSITORY_PERSONAS_V1 = V1_SERVICE.concat("personas");
    
    /**
     * Rutas de servicio para la versión 1 (V1)
     */
    private static final String PATH_SERVICE_PERSONAS_V1 = V1_REPOSITORY.concat("personas");
}