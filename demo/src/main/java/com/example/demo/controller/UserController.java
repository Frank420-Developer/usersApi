package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.api.UserServicePort;
import com.example.demo.domain.data.UserDto;
import com.example.demo.domain.data.UserRequestBody;
import com.example.demo.infrastructure.entity.Users;
import com.example.demo.infrastructure.exceptions.UserNotFound;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserServicePort userServicePort;
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers(@RequestParam(required = false, defaultValue = "0") int page, 
													@RequestParam(required = false, defaultValue = "20") int size,
													@RequestParam(required = false, defaultValue = "creationDate,desc") String[] sort){
		List<UserDto> usuarios = userServicePort.getAllUsers(page, size, sort);
		
		HttpHeaders hr = new HttpHeaders();
		long totalElements = userServicePort.countRegisters();
		hr.add("Total-Elements", String.valueOf(totalElements));
		
		return new ResponseEntity<>(usuarios, hr, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable UUID id){
		Users user = userServicePort.getUserById(id);
		if(user != null)
			return new ResponseEntity<>(user, HttpStatus.OK);
		else
			throw new UserNotFound("not found");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable UUID id, @Valid @RequestBody UserRequestBody userRequestBody){
		Users u = userServicePort.getUserById(id);
		if(u != null) {
			u = userServicePort.putUser(u, userRequestBody);
			return new ResponseEntity<>(u, HttpStatus.OK);
		}
			
		throw new UserNotFound("not found");
		
	}
	
	
	
}
