package com.example.demo.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.api.UserServicePort;
import com.example.demo.domain.service.UserServiceImpl;
import com.example.demo.domain.spi.UserPersistancePort;
import com.example.demo.infrastructure.adapter.UserJpaAdapter;

@Configuration
public class UserConfig {

	@Bean
	public UserPersistancePort userPersistance() {
		return new UserJpaAdapter();
	}
	
	@Bean
	public UserServicePort userServicePort() {
		return new UserServiceImpl(userPersistance());
	}
}
