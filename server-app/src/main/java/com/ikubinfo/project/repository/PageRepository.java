package com.ikubinfo.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import com.ikubinfo.project.entity.Page;
import com.ikubinfo.project.entity.PageLiked;
import com.ikubinfo.project.entity.User;
import com.ikubinfo.project.util.PersistenceSingleton;

public class PageRepository {
	private EntityManager em;

	public PageRepository() {
		this.em = PersistenceSingleton.INSTANCE.getEntityManagerFactory().createEntityManager();
	}
	
	public List<Page> getAllPages(){
		return em.createNamedQuery("Page.getAllPages",Page.class).setParameter(1, true).getResultList();
	}
 	@Transactional
	public Page createPage(Page page) {
		em.getTransaction().begin();
		em.persist(page);
		em.getTransaction().commit();
		em.close();
		return page;
	}

	@Transactional
	public void editPage(Page page) {
		em.getTransaction().begin();
		em.merge(page);
		em.getTransaction().commit();
		em.close();
	}

	public List<Page> getMyPages(final String email) {
		TypedQuery<Page> query = em.createNamedQuery("Page.getMyPages", Page.class);
		query.setParameter(1, email);
		query.setParameter(2, true);
		return query.getResultList();
	}

	@Transactional
	public void likePage(PageLiked likedPage) {
		em.getTransaction().begin();
		em.persist(likedPage);
		em.getTransaction().commit();
		em.close();

	}

	public Page getPageById(final long id) {
		TypedQuery<Page> query = em.createNamedQuery("Page.getPageById", Page.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return query.getSingleResult();
	}

	public Page isLiked(Page page, User user) {
		TypedQuery<Page> query = em.createQuery("Select p.page from PageLiked p where p.user=?1 and p.page=?2",
				Page.class);
		query.setParameter(1, user);
		query.setParameter(2, page);
		return query.getSingleResult();
	}

	@Transactional
	public void update(PageLiked pageLiked) {
		em.getTransaction().begin();
		em.merge(pageLiked);
		em.getTransaction().commit();
		em.close();
	}

}
