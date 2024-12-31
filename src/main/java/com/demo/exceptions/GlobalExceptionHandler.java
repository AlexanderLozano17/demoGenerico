package com.demo.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.helperEntities.ApiResponseHelperEntity;
import com.demo.utils.ConstantsUtils;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de validación (errores de campos inválidos en las solicitudes).
     *
     * @param ex La excepción de validación.
     * @return Respuesta con los detalles de los errores de validación.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseHelperEntity> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> listErrorMessages = extractValidationErrors(ex);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ConstantsUtils.MESSAGE_BAD_REQUEST, listErrorMessages, null);
    }

    /**
     * Maneja excepciones de tipo NotFound (cuando no se encuentra un recurso).
     *
     * @param ex La excepción de recurso no encontrado.
     * @return Respuesta con un mensaje de error adecuado.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponseHelperEntity> handleNotFoundException(NotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ConstantsUtils.MESSAGE_NOT_FOUND, null, null);
    }
        
    /**
     * Maneja excepciones de tipo HttpRequestMethodNotSupportedException (método HTTP no soportado).
     *
     * @param ex La excepción de método HTTP no soportado.
     * @return Respuesta con el error adecuado.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponseHelperEntity> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        String errorMessage = ConstantsUtils.MESSAGE_METHOD_NOT_ALLOWED + " " + ex.getMessage();
        return buildErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, errorMessage, null, null);
    }
    
    /**
     * Maneja excepciones de tipo ConstraintViolationException (violación de restricciones).
     *
     * @param ex La excepción de violación de restricciones.
     * @return Respuesta con el error adecuado.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponseHelperEntity> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = ConstantsUtils.MESSAGE_BAD_REQUEST_1 + ex.getMessage();
        return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMessage, null, null);
    }
    
    /**
     * Maneja excepciones de tipo DataIntegrityViolationException (violación de integridad de datos).
     *
     * @param ex La excepción de violación de integridad de datos.
     * @return Respuesta con el error adecuado.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponseHelperEntity> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String errorMessage = ConstantsUtils.MESSAGE_CONFLICT + ex.getMostSpecificCause().getMessage();
        return buildErrorResponse(HttpStatus.CONFLICT, errorMessage, null, null);
    }

    /**
     * Maneja excepciones de tipo AccessDeniedException (acceso denegado).
     *
     * @param ex La excepción de acceso denegado.
     * @return Respuesta con el error adecuado.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponseHelperEntity> handleAccessDeniedException(AccessDeniedException ex) {
        return buildErrorResponse(HttpStatus.FORBIDDEN, ConstantsUtils.MESSAGE_FORBIDDEN, null, null);
    }
    
    /**
    * Maneja excepciones de tipo Exception que no hayan sido tenido en cuenta en esta clase.
    *
    * @param ex La excepción.
    * @return Respuesta con el error adecuado.
    */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseHelperEntity> handleInternalServerError(Exception ex) {
    	String errorMessage = ConstantsUtils.MESSAGE_INTERNAL_SERVER_ERROR + ex.getMessage();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiResponseHelperEntity.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage, null, null));
	}

    /**
     * Extrae los errores de validación y los convierte en una lista de mensajes.
     *
     * @param ex La excepción de validación.
     * @return Lista de mensajes de error.
     */
    private List<String> extractValidationErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .map(objectError -> {
                    FieldError fieldError = (FieldError) objectError;
                    return fieldError.getField() + ": " + objectError.getDefaultMessage();
                })
                .collect(Collectors.toList());
    }

    /**
     * Construye una respuesta de error personalizada para la API.
     *
     * @param status El estado HTTP de la respuesta.
     * @param message El mensaje general de error.
     * @param errors La lista de errores específicos (si existen).
     * @param details Detalles adicionales (si los hay).
     * @return La respuesta de error.
     */
    private ResponseEntity<ApiResponseHelperEntity> buildErrorResponse(HttpStatus status, String message, List<String> errors, Object details) {
        ApiResponseHelperEntity response = ApiResponseHelperEntity.error(status.value(), message, errors, details);
        return ResponseEntity.status(status).body(response);
    }
}
