package com.ikubinfo.project.repository;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.ikubinfo.project.entity.Friends;
import com.ikubinfo.project.entity.User;
import com.ikubinfo.project.util.PersistenceSingleton;

public class UserRepository {
	private EntityManager em;

	public UserRepository() {
		this.em = PersistenceSingleton.INSTANCE.getEntityManagerFactory().createEntityManager();
	}
	
	public List<User> getAllUsers(User user) {
		return em.createQuery("Select DISTINCT u from User u where ( u NOT IN (Select f.user from Friends f where f.friend=?2 and f.flag=?1) and "
				+ "u NOT IN (Select f.friend from Friends f where f.user=?2 and f.flag=?1) ) and u <> ?2 and u.flag=?1",User.class)
				.setParameter(1, true)
				.setParameter(2, user)
				.getResultList();
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
	
	@Transactional
	public User register(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		return user;
	}
	
	@Transactional
	public User update(User user) {
		em.getTransaction().begin();
		User updatedUser=em.merge(user);
		em.getTransaction().commit();
		return updatedUser;
	}
	
	@Transactional
	public Friends addFriend(Friends friends) {
		em.getTransaction().begin();
		em.merge(friends);
		em.getTransaction().commit();
		return friends;
	}
	
	
	
}
