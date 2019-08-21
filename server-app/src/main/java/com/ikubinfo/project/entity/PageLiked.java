package com.ikubinfo.project.entity;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="page_liked",schema="socialnetwork")
public class PageLiked {
	
	@EmbeddedId
	private PageLikedId id;
	
	@NotNull
	@ManyToOne
	@MapsId("userId")
	private User user;
	
	@NotNull
	@ManyToOne
	@MapsId("pageId")
	private Page page;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	private boolean flag;
	
	public PageLiked() {
		
	}

	public PageLikedId getId() {
		return id;
	}

	public void setId(PageLikedId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
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

	@Override
	public String toString() {
		return "PageLiked [id=" + id + ", user=" + user + ", page=" + page + ", date=" + date + ", flag=" + flag + "]";
	}
	
}
