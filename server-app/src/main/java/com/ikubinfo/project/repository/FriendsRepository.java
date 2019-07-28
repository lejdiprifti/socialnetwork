package com.ikubinfo.project.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.ikubinfo.project.entity.Friends;
import com.ikubinfo.project.util.PersistenceSingleton;

public class FriendsRepository {
	private EntityManager em;
	public FriendsRepository() {
		this.em=PersistenceSingleton.INSTANCE.getEntityManagerFactory().createEntityManager();
	}
	
	@Transactional
	public void acceptFriendRequest(Friends friends) {
		em.getTransaction().begin();
		em.merge(friends);
		em.getTransaction().commit();
	
	}
	
	public Friends getRequest(final long id) {
		TypedQuery<Friends> query=em.createQuery("Select f from Friends f where f.flag=?1",Friends.class);
		query.setParameter(1, true);
		return query.getSingleResult();
	}

}
