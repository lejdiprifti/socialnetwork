package com.ikubinfo.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import javax.transaction.Transactional;

import com.ikubinfo.project.entity.Chat;
import com.ikubinfo.project.entity.User;
import com.ikubinfo.project.util.PersistenceSingleton;

public class ChatRepository {
	private EntityManager em;
	public ChatRepository() {
		this.em=PersistenceSingleton.INSTANCE.getEntityManagerFactory().createEntityManager();
	}
	
	public List<Chat> getMessages(User user,User reciever){
		TypedQuery<Chat> query=em.createNamedQuery("Chat.getMessages", Chat.class);
		query.setParameter(1, user);
		query.setParameter(2, reciever);
		query.setParameter(3, true);
		return query.getResultList();
	}
	
	@Transactional
	public void sendMessage(Chat chat) {
		em.getTransaction().begin();
		em.persist(chat);
		em.getTransaction().commit();
		em.close();
	}
	
	

}
