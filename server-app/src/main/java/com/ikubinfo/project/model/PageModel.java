package com.ikubinfo.project.model;

import java.util.Date;

import com.ikubinfo.project.entity.RoleEntity;

public class PageModel {

	private long id;
	private String name;
	private String bio;
	private Date date;
	private UserModel user;
	private RoleEntity role;
	private boolean flag;

	public PageModel() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
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

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "PageModel [id=" + id + ", name=" + name + ", bio=" + bio + ", user=" + user + ", role=" + role
				+ ", flag=" + flag + "]";
	}

}
