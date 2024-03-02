package com.mycompany.modelo;

import javax.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Rol(Long id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public Rol() {
		
	}

	public Rol(String username) {
		super();
		this.username = username;
	}

	
}
