package com.ikubinfo.project.model;

import java.util.Date;


public class ChatModel {
	
	private long id;
	private UserModel user;
	private UserModel reciever;
	private String message;
	private Date date;
	private boolean flag;
	
	public ChatModel() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserModel getUser() {
		return user;
	}

	public void setSender(UserModel user) {
		this.user = user;
	}

	public UserModel getReciever() {
		return reciever;
	}

	public void setReciever(UserModel reciever) {
		this.reciever = reciever;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
