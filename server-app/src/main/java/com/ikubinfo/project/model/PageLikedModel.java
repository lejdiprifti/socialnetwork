package com.ikubinfo.project.model;

import java.util.Date;

import com.ikubinfo.project.entity.Page;
import com.ikubinfo.project.entity.PageLikedId;

public class PageLikedModel {
	
	private PageLikedId id;
	private UserModel user;
	private PageModel page;
	private Date date;
	private boolean flag;
	public PageLikedModel() {
		
	}
	
	public PageLikedId getId() {
		return id;
	}

	public void setId(PageLikedId id) {
		this.id = id;
	}

	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public PageModel getPage() {
		return page;
	}
	public void setPage(PageModel page) {
		this.page = page;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
