package com.example.demo.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.domain.data.UserDto;
import com.example.demo.infrastructure.entity.Users;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	List<UserDto> usersListToUserDtoList(Iterable<Users> usersList); 
}
