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
public final class ConstantsPathApiConfig {
    
    // Constructor privado para evitar instanciación
    private ConstantsPathApiConfig () {
        // Constructor vacío, no permite la creación de instancias
    }

    // Prefijo base para todas las rutas de la API
    public static final String DEMO = "demo_generico";
    
    /********************************************************************************
     *           RUTAS DE VERSIONES DE LA API
     ********************************************************************************/    
    private static final String SERVICE = "service_";    
    private static final String REPOSITORY = "repository_";    
    private static final String CONTROLLER_V1 = "api/v1";
    private static final String CONTROLLER_V2 = "api/v2";
    
    /********************************************************************************
     *           NOMBRE ÚNICO SERVICIO, CONTROLADOR Y REPOSITORIO PARA LA VERSION 1
     ********************************************************************************/
    
    /**
     * Rutas de servicio para la versión 1 (V1)
     */
    public static final String PATH_SERVICE_USER_V1 = SERVICE + "user";
    public static final String PATH_SERVICE_PERSON_V1 = SERVICE + "person";
    
    /**
     * Rutas de repositorio para la versión 1 (V1)
     */
    public static final String PATH_REPOSITORY_USER = REPOSITORY + "user";
    public static final String PATH_REPOSITORY_PERSON = REPOSITORY  + "person";
    
    /**
     * Rutas de controlador para la versión 1 (V1)
     */
    public static final String PATH_CONTROLLER_USER_V1 = CONTROLLER_V1 + "/user";
    public static final String PATH_CONTROLLER_PERSON_V1 = CONTROLLER_V2 + "/person";    
           
}