package com.assessment.momentum.dao.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.assessment.momentum.dao.interfaces.UserDAOLocal;
import com.assessment.momentum.entity.User;

/**
 * Session Bean implementation class UserDAO
 */
@Stateless
@LocalBean
public class UserDAO implements UserDAOLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public UserDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User findUserById(int id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public User findUserByUsernamePassword(String username, String password) {
		try {
			return entityManager.createNamedQuery("Users.findByUsernameAndPassword", User.class)
					.setParameter("username", username).setParameter("password", password).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public User addUsers(User user) {
		entityManager.persist(user);
		return user;
	}

}
