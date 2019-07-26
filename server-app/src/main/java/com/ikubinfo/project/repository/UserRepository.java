package com.ikubinfo.project.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.ikubinfo.project.entity.User;
import com.ikubinfo.project.model.UserModel;
import com.ikubinfo.project.util.PersistenceSingleton;

public class UserRepository {
	private EntityManager entityManager;

	public UserRepository() {
		this.entityManager = PersistenceSingleton.INSTANCE.getEntityManagerFactory().createEntityManager();
	}
	
	public User getUser(int id) {
		return entityManager.find(User.class, id);
	}

	public User getUserByUsername(String username) {
		TypedQuery<User> query=entityManager.createQuery("From User where username=?1",User.class);
		query.setParameter(1,username);
		return query.getSingleResult();
	}
}
