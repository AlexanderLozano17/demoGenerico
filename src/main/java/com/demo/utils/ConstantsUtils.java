package com.demo.utils;

/**
 * La clase ConstantsUtils es una clase utilitaria en Java que define constantes
 * utilizadas a lo largo de la aplicación, especialmente para manejar mensajes
 * de respuesta y errores. La clase está diseñada con un constructor privado
 * para evitar su instanciación, dado que solo contiene constantes. Es útil para
 * centralizar las variables que no son de configuración y se utilizan en diferentes partes de la
 * aplicación, mejorando la mantenibilidad del código al evitar la duplicación de contenido
 */
public final class ConstantsUtils {

    private ConstantsUtils() {
    	// Constructor privado para evitar instanciación
    }
    
    /**
     * Mensajes de respuesta para las consulta a datos
     */
	public final static String DATA_LIST_OK = "Datos listados con éxito.";
	public final static String DATA_SAVE_OK = "Registro Guardado con exito";
	public final static String DATA_UPDATE_OK = "Dato actualizado con éxito";
	public final static String DATA_DELETE_OK = "Registro eliminado con éxito.";
	public final static String DATA_NOT_FOUND = "No se han encontrado registros.";
	
	
	/**
    * Mensajes para las respuestas de las Excepciones manejadas por la clase GlobalExceptionHandler
    */
	public static final String MESSAGE_BAD_REQUEST = "Ha ocurrido un error al procesar los datos proporcionados. Por favor, verifique la información ingresada y vuelva a intentarlo. ";
	public static final String MESSAGE_BAD_REQUEST_1 = "Error de validación en la base de datos: ";
	public static final String MESSAGE_NOT_FOUND = "El recurso solicitado no se ha encontrado. Por favor, verifique la URL o intente con otra solicitud. ";
	public static final String MESSAGE_METHOD_NOT_ALLOWED = "Método no permitido para la solicitud. ";
	public static final String MESSAGE_CONFLICT = "Violación de integridad de datos. ";
	public static final String MESSAGE_FORBIDDEN = "Acceso denegado. No tiene permisos suficientes. ";
    public static final String MESSAGE_INTERNAL_SERVER_ERROR = "Error en el servidor. Por favor, inténtelo más tarde. Detalles: ";
    public static final String MESSAGE_ERROR_ENTITYMANAGER = "EntityManager no está configurado correctamente.";
}
