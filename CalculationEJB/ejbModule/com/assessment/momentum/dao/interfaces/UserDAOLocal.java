package com.assessment.momentum.dao.interfaces;

import javax.ejb.Local;

import com.assessment.momentum.entity.User;

@Local
public interface UserDAOLocal {
		public User findUserById(int id);
		public User findUserByUsernamePassword(String username, String password );	
		public User addUsers(User user);
}
