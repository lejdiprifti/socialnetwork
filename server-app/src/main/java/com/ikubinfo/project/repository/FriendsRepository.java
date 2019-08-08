package com.ikubinfo.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.ikubinfo.project.entity.Friends;
import com.ikubinfo.project.entity.FriendsId;
import com.ikubinfo.project.entity.User;
import com.ikubinfo.project.util.PersistenceSingleton;

public class FriendsRepository {
	private EntityManager em;

	public FriendsRepository() {
		this.em = PersistenceSingleton.INSTANCE.getEntityManagerFactory().createEntityManager();
	}

	@Transactional
	public void acceptFriendRequest(Friends friends) {
		em.getTransaction().begin();
		em.merge(friends);
		em.getTransaction().commit();
		em.close();
	}

	public Friends getRequest(FriendsId id) {
		TypedQuery<Friends> query = em.createQuery("Select f from Friends f where f.id=?1 and f.flag=?2",
				Friends.class);
		query.setParameter(2, true);
		query.setParameter(1, id);
		return query.getSingleResult();
	}

	public List<Friends> getRequests(User user) {
		TypedQuery<Friends> query = em
				.createQuery("Select f from Friends f where f.user=?1 and f.accepted=?2 and f.flag=?3", Friends.class);
		query.setParameter(2, false);
		query.setParameter(3, true);
		query.setParameter(1, user);
		return query.getResultList();
	}

	public List<Friends> getMyRequests(User user) {
		TypedQuery<Friends> query = em.createQuery(
				"Select f from Friends f where f.friend=?1  and f.accepted=?2 and f.flag=?3", Friends.class);
		query.setParameter(2, false);
		query.setParameter(3, true);
		query.setParameter(1, user);
		return query.getResultList();
	}

	public List<User> getFriends(User user) {
		TypedQuery<User> query = em.createQuery(
				"Select f.user from Friends f where f.friend = ?2 and f.user<>?2 and f.accepted=?3 and f.flag=?1 ",
				User.class);
		TypedQuery<User> query2 = em.createQuery(
				" Select f.friend from Friends f where f.user=?2 and f.friend<>?2 and f.accepted=?3 and f.flag=?1",
				User.class);
		query.setParameter(1, true);
		query.setParameter(2, user);
		query.setParameter(3, true);
		query2.setParameter(1, true);
		query2.setParameter(2, user);
		query2.setParameter(3, true);
		List<User> list = query.getResultList();
		list.addAll(query2.getResultList());

		return list;
	}

}
