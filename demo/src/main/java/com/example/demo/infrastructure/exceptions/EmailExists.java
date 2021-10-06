package com.example.demo.infrastructure.exceptions;

public class EmailExists extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmailExists(String message) {
		super(message);
	}
}
