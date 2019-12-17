package com.assessment.momentum.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@NamedQuery(name = "Users.findByUsernameAndPassword", query = "SELECT u FROM User u where u.username = :username and u.password = :password")
public class User implements Serializable {	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NotNull
	@Length(max=25)
	private String username;
	@NotNull
	@Length(max=25)
	private String password;
	@NotNull
	@Length(max =5)
	private String role;
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}
   
}
