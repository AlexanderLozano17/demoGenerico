package com.demo.config;

public final class ConstantesPathApi {
	
	private static final String DEMO = "demo_";
	
	/********************************************************************************
	 *           VERSIÓN API
	 ********************************************************************************/
	private static final String V1_CONTROLLER = DEMO.concat("api/v1/");
	private static final String V2_CONTROLLER = DEMO.concat("api/v2/");
	
	private static final String V1_SERVICE = DEMO.concat("v1_Service");
	private static final String V2_SERVICE = DEMO.concat("v2_Service");
	
	private static final String V1_REPOSITORY = DEMO.concat("v1_Repository");
	private static final String V2_REPOSITORY = DEMO.concat("v2_Repository");
	
	
	/********************************************************************************
	 *           NOMBRE ÚNICO SERVICIO, CONTROLADOR U REPOSITORIO VERSION 1
	 ********************************************************************************/
	
	/**
	 * RestController
	 */
	private static final String PATH_CONTROLLER_PERSONAS_V1 = V1_CONTROLLER.concat("personas");
	
	
	
	/**
	 * Repositories
	 */
	private static final String PATH_REPOSITORY_PERSONAS_V1 = V1_SERVICE.concat("personas");
	
	
	
	/**
	 * Service
	 */
	private static final String PATH_SERVICE_PERSONAS_V1 = V1_REPOSITORY.concat("personas");
	
}
