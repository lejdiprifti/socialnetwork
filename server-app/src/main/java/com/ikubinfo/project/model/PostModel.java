package com.ikubinfo.project.model;

import java.util.Date;
import java.util.List;

import com.ikubinfo.project.entity.PostLiked;
import com.ikubinfo.project.entity.User;

public class PostModel {
	
	private long id;
	private String title;
	private String description;
	private Date date;
	private UserModel user;
	private List<PostLikedModel> likes;
	private boolean flag;
	
	public PostModel() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}


	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<PostLikedModel> getLikes() {
		return likes;
	}

	public void setLikes(List<PostLikedModel> likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "PostModel [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date
				+ ", user=" + user + ", flag=" + flag + "]";
	}

}
