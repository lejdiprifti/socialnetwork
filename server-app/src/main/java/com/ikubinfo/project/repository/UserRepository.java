package com.ikubinfo.project.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.ikubinfo.project.entity.User;
import com.ikubinfo.project.model.UserModel;
import com.ikubinfo.project.util.PersistenceSingleton;

public class UserRepository {
	private EntityManager em;

	public UserRepository() {
		this.em = PersistenceSingleton.INSTANCE.getEntityManagerFactory().createEntityManager();
	}
	
	public User getUserById(long id) {
		TypedQuery<User> query=em.createNamedQuery("User.getById",User.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return query.getSingleResult();
	}

	public User getUserByEmail(String email) {
		TypedQuery<User> query=em.createNamedQuery("User.getByEmail",User.class);
		query.setParameter(1,email);
		query.setParameter(2,true);
		return query.getSingleResult();
	}

	public User register(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		return user;
	}

	
}
