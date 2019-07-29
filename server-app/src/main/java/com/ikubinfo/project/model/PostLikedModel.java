package com.ikubinfo.project.model;
import java.util.Date;

import  com.ikubinfo.project.entity.PostLikedId;
public class PostLikedModel {
	
	private PostLikedId id;
	private UserModel user;
	private PostModel post;
	private Date date;
	private boolean flag;
	
	public PostLikedModel() {
		
	}

	public PostLikedId getId() {
		return id;
	}

	public void setId(PostLikedId id) {
		this.id = id;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public PostModel getPost() {
		return post;
	}

	public void setPost(PostModel post) {
		this.post = post;
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
