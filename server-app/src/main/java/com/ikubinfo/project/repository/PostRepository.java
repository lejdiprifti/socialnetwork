package com.ikubinfo.project.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.ikubinfo.project.entity.Post;
import com.ikubinfo.project.util.PersistenceSingleton;

public class PostRepository {

	private EntityManager em;

	public PostRepository() {
		this.em = PersistenceSingleton.INSTANCE.getEntityManagerFactory().createEntityManager();
	}
	
	public List<Post> getPosts(){
		return em.createNamedQuery("Post.getPosts",Post.class)
				.setParameter(1,true)
				.getResultList();
	}
	
	public Post getPostById(final long id) {
		return em.createNamedQuery("Post.getPostById",Post.class)
				.setParameter(1,id)
				.setParameter(2, true)
				.getSingleResult();
	}
	
	@Transactional
	public Post addPost(Post post) {
		
		em.getTransaction().begin();
		em.persist(post);
		em.getTransaction().commit();
		return post;
	}
	
	@Transactional
	public Post update(Post post) {
		em.getTransaction().begin();
		Post mergedPost=em.merge(post);
		em.getTransaction().commit();
		return mergedPost;
	}
	
}
