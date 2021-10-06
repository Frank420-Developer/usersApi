package com.example.demo.domain.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.api.UserServicePort;
import com.example.demo.domain.data.UserDto;
import com.example.demo.domain.data.UserRequestBody;
import com.example.demo.domain.spi.UserPersistancePort;
import com.example.demo.infrastructure.entity.Users;

public class UserServiceImpl implements UserServicePort{

	private UserPersistancePort userPersistancePort;
	
	public UserServiceImpl(UserPersistancePort userPersistancePort) {
		this.userPersistancePort = userPersistancePort;
	}

	@Override
	public List<UserDto> getAllUsers(int page, int size, String[] sort) {
		return userPersistancePort.getAllUsers(page, size, sort);
	}

	@Override
	public Users getUserById(UUID id) {
		return userPersistancePort.getUserById(id);
	}

	@Override
	public Users putUser(Users user, UserRequestBody userRequestBody) {
		return userPersistancePort.putUser(user, userRequestBody);
	}

	@Override
	public long countRegisters() {
		return userPersistancePort.countRegisters();
	}

}
