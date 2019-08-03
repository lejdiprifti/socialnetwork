package com.ikubinfo.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.ikubinfo.project.entity.Friends;
import com.ikubinfo.project.entity.User;
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
		TypedQuery<Friends> query=em.createQuery("Select f from Friends f where f.id=?1 and f.flag=?2",Friends.class);
		query.setParameter(2, true);
		query.setParameter(1, id);
		return query.getSingleResult();
	}
	
	public List<Friends> getRequests(User user) {
		TypedQuery<Friends> query=em.createQuery("Select f from Friends f where ( f.friend=?1 or f.user=?1 ) and f.accepted=?2 and f.flag=?3",Friends.class);
		query.setParameter(2, false);
		query.setParameter(3, true);
		query.setParameter(1, user);
		return query.getResultList();
	}
	
	@Transactional
	public void deleteRequest(Friends friends) {
		em.getTransaction().begin();
		em.merge(friends);
		em.getTransaction().commit();
	}

}
