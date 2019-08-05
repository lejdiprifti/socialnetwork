package com.ikubinfo.project.model;

import java.util.Date;

import com.ikubinfo.project.entity.ChatMessageId;

public class ChatModel {
	
	private ChatMessageId id;
	private UserModel sender;
	private UserModel reciever;
	private String message;
	private Date date;
	private boolean flag;
	
	public ChatModel() {
		
	}

	public ChatMessageId getId() {
		return id;
	}

	public void setId(ChatMessageId id) {
		this.id = id;
	}

	public UserModel getSender() {
		return sender;
	}

	public void setSender(UserModel sender) {
		this.sender = sender;
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
