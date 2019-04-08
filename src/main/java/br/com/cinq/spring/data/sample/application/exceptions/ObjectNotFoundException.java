package br.com.cinq.spring.data.sample.application.exceptions;

/**
 * Custom exception for object not found
 * 
 * @author Tadeu Augusto Dutra Pinto
 *
 */
public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}

	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}