package com.assessment.momentum.managedbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import com.assessment.momentum.dao.interfaces.UserDAOLocal;
import com.assessment.momentum.entity.User;

@SuppressWarnings("deprecation")
@ManagedBean(name="userMB")
@ViewScoped
public class UserManagedBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private UserDAOLocal userDAOLocal;
	
	private int id;
	private String username;
	private String password;
	private String role;
	private String error;
	private int isAdmin;
	
	public void findByUserId() {
		User user = userDAOLocal.findUserById(id);
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.role = user.getRole();
	}

	public String validateUserCredentials() {
		String passPage = "basiccalculator";
		String failPage = "index";
		User user = new User();
		user = userDAOLocal.findUserByUsernamePassword(this.username, this.password);
		if(user != null) {
			this.error = "";
			this.id = user.getId();
			this.username = user.getUsername();
			this.password = user.getPassword();
			this.role = user.getRole();

			checkIfItsAdmin();
			return passPage;
		}
		this.error = "User combination incorrect";
		return failPage;	
	}
	
	public void checkIfItsAdmin() {
		if(this.role.equals("admin"))
			this.isAdmin = 1;
		else
			this.isAdmin = 0;
	}
	
	public int getIsAdmin() {
		return isAdmin;
	}


	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
