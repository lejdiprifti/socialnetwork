package com.ikubinfo.project.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.ikubinfo.project.entity.Post;
import com.ikubinfo.project.entity.PostLiked;
import com.ikubinfo.project.entity.User;
import com.ikubinfo.project.util.PersistenceSingleton;

public class PostRepository {

	private EntityManager em;
	public PostRepository() {
		this.em = PersistenceSingleton.INSTANCE.getEntityManagerFactory().createEntityManager();
	}
	 
	public List<Post> getPosts(User iUser){
		return em.createNamedQuery("Post.getPosts",Post.class)
				.setParameter(1,true) 
				.setParameter(2, iUser )
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
		em.close();
		return post;
	}
	
	@Transactional
	public Post update(Post post) {
		em.getTransaction().begin();
		Post mergedPost=em.merge(post);
		em.getTransaction().commit();
		em.close();
		return mergedPost;
	}
	
	@Transactional
	public void like(PostLiked post) {
		em.getTransaction().begin();
		em.persist(post);
		em.getTransaction().commit();
		em.close();
	}
	
	@Transactional
	public void updateLike(PostLiked post) {
		em.getTransaction().begin();
		em.merge(post);
		em.getTransaction().commit();
		em.close();
	}
	
	public PostLiked isAlreadyLiked(Post post,User user) {
		TypedQuery<PostLiked> query=em.createQuery("Select p from PostLiked p where p.post=?1 and p.user=?2",PostLiked.class);
	query.setParameter(1, post);
	query.setParameter(2, user);
	return query.getSingleResult();
	}
	
	public List<Post> getMyPosts(User user) {
		TypedQuery<Post> query=em.createNamedQuery("Post.getMyPosts",Post.class);
		query.setParameter(1, user);
		query.setParameter(2, true);
		return query.getResultList();
	}
}
