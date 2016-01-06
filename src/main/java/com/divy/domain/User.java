package com.divy.domain;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.hibernate.annotations.Table;


@Entity
@javax.persistence.Table(name="user")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false, updatable=false)
	private Integer id;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="password_hase", nullable=false)
	private String passwordHash;
	
	@Column(name="role", nullable=false)
	@Enumerated(EnumType.STRING)
	private Role role;
	

	public Integer getId() {
		return id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	
}
