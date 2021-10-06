package com.example.demo.infrastructure.exceptions;

public class InvalidRole extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidRole(String message) {
		super(message);
	}
}
