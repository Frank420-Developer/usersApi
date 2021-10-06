package com.example.demo.domain.api;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.data.UserDto;
import com.example.demo.domain.data.UserRequestBody;
import com.example.demo.infrastructure.entity.Users;

public interface UserServicePort {
	List<UserDto> getAllUsers(int page, int size, String[] sort);
	Users getUserById(UUID id);
	Users putUser(Users u, UserRequestBody userRequestBody);
	long countRegisters();
}
