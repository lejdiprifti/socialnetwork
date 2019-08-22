package com.ikubinfo.project.service;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.NotFoundException;

import com.ikubinfo.project.converter.PageConverter;
import com.ikubinfo.project.converter.UserConverter;
import com.ikubinfo.project.entity.Page;
import com.ikubinfo.project.entity.PageLiked;
import com.ikubinfo.project.entity.PageLikedId;
import com.ikubinfo.project.entity.User;
import com.ikubinfo.project.model.PageModel;
import com.ikubinfo.project.repository.PageRepository;
import com.ikubinfo.project.repository.UserRepository;

public class PageService {

	private PageRepository pageRepository;
	private UserRepository userRepository;
	private UserConverter userConverter;
	private PageConverter pageConverter;

	public PageService() {
		this.pageRepository = new PageRepository();
		this.userRepository = new UserRepository();
		this.userConverter = new UserConverter();
		this.pageConverter = new PageConverter();
	}

	public List<PageModel> getMyPages(String email) {
		return pageConverter.toModel(pageRepository.getMyPages(email));
	}

	public PageModel getPageById(final long id) {
		try {
			return pageConverter.toModel(pageRepository.getPageById(id));
		} catch (NoResultException e) {
			throw new NotFoundException("Page not found.");
		}
	}

	public void likePage(String email, long id) {
		User user = userRepository.getUserByEmail(email);
		Page page = pageRepository.getPageById(id);
		PageLikedId pageLikedId = new PageLikedId();
		pageLikedId.setPageId(page.getId());
		pageLikedId.setUserId(user.getId());
		PageLiked pageLiked = new PageLiked();
		pageLiked.setId(pageLikedId);
		pageLiked.setPage(page);
		pageLiked.setUser(user);
		pageLiked.setDate(new Date());
		pageLiked.setFlag(true);
		try {
			pageRepository.isLiked(page, user);
			pageRepository.update(pageLiked);
		} catch (NoResultException e) {
			pageRepository.likePage(pageLiked);
		}
	}

	public Page createPage(PageModel page, String email) {
		page.setFlag(true);
		page.setDate(new Date());
		page.setUser(userConverter.toModel(userRepository.getUserByEmail(email)));
		return pageRepository.createPage(pageConverter.toEntity(page));
	}

	public void editPage(PageModel page) {
		try {
			Page foundPage = pageRepository.getPageById(page.getId());
			if (page.getName() != "") {
				foundPage.setName(page.getName());
			}
			if (page.getBio() != "") {
				foundPage.setBio(page.getBio());
			}

			pageRepository.editPage(foundPage);
		} catch (NoResultException e) {
			throw new NotFoundException();
		}
	}

	public void unlikePage(String email, long id) {
		User user = userRepository.getUserByEmail(email);
		Page page = pageRepository.getPageById(id);
		PageLikedId pageLikedId = new PageLikedId();
		pageLikedId.setPageId(page.getId());
		pageLikedId.setUserId(user.getId());
		PageLiked pageLiked = new PageLiked();
		pageLiked.setId(pageLikedId);
		pageLiked.setPage(page);
		pageLiked.setUser(user);
		pageLiked.setDate(new Date());
		pageLiked.setFlag(false);
		pageRepository.update(pageLiked);
	}

}
