package com.fatec.evento.services.exceptions;

public class InvalidLoginException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidLoginException(Object email, Object senha) {
		super("unable to login to email: " +email+ " and password: " +senha);
	}
}
