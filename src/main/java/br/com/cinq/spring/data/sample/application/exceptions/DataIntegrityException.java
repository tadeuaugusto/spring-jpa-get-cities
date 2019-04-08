package br.com.cinq.spring.data.sample.application.exceptions;

/**
 * Custom exception for data integrity
 * 
 * @author Tadeu Augusto Dutra Pinto
 *
 */
public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String msg) {
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
}