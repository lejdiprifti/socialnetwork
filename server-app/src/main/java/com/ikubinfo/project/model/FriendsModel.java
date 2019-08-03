package com.ikubinfo.project.model;

import java.util.Date;

import com.ikubinfo.project.entity.FriendsId;


public class FriendsModel {
	private FriendsId id;
	private UserModel friend;
	private UserModel user;
	private Date date;
	private boolean accepted;
	private boolean flag;
	public FriendsModel() {
		
	}
	
	
	public FriendsId getId() {
		return id;
	}


	public void setId(FriendsId id) {
		this.id = id;
	}


	public UserModel getFriend() {
		return friend;
	}
	public void setFriend(UserModel friend) {
		this.friend = friend;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	
}
