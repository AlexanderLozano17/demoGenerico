package com.demo.helperEntities;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ApiResponseHelperEntity<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int status;
	private String message;	
	private List<String> errors = null;
	private T data = null;
	
	public ApiResponseHelperEntity() {
		super();
	}
	
	/**
	 * 
	 * @param status
	 * @param message
	 * @param errors
	 * @param data
	 */
	public ApiResponseHelperEntity(int status, String message, List<String> errors, T data) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
		this.data = data;
	}

	/**
	 * 
	 * @param status
	 * @param message
	 * @param data
	 */
	public ApiResponseHelperEntity(int status, String message, T data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
     *  Método de ayuda para crear una respuesta exitosa
     * @param <T>
     * @param statusCode
     * @param message
     * @param data
     * @return
     */
    public static <T> ApiResponseHelperEntity<T> success(int statusCode, String message, T data) {
        return new ApiResponseHelperEntity<>(statusCode, message, data);
    }

    /**
     * Método de ayuda para crear una respuesta de error 
     * @param <T>
     * @param statusCode
     * @param message
     * @return
     */
    public static <T> ApiResponseHelperEntity<T> error(int statusCode, String message, List<String> errors, T data) {
        return new ApiResponseHelperEntity<>(statusCode, message, errors, data);
    }
}
