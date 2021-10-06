package com.example.demo.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.infrastructure.entity.Users;

@Repository
public interface UsersRepository extends PagingAndSortingRepository<Users, UUID>{
	Users findByEmailIgnoreCase(String email);
}
