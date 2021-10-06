package com.example.demo.infrastructure.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class Users {
	@Id
	@Column(name = "id_user")
	private UUID id;
	
	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	
	@Column(name = "modification_date")
	private LocalDateTime modificationDate;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "vacation_start")
	private LocalDateTime vacationStart;
	
	@Column(name = "vacation_ending")
	private LocalDateTime vacationEnd;
	
	@Column(name = "role")
	private String role;

	public Users(String email, String name, String lastName, LocalDateTime vacationStart, LocalDateTime vacationEnd, String role) {
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.vacationStart = vacationStart;
		this.vacationEnd = vacationEnd;
		this.role = role;
	}
	
	
	
	
}
