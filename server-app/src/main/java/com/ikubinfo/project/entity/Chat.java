package com.ikubinfo.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="chat")
public class Chat {

	public Chat() {
		
	}
	
	@EmbeddedId
	private ChatMessageId id;
	
	@ManyToOne
	@MapsId("senderId")
	private User sender;
	
	@ManyToOne
	@MapsId("recieverId")
	private User reciever;
	
	@Column(name="message",nullable=false,length=1000000)
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date")
	private Date date;
	
	@Column(name="flag")
	private boolean flag;

	public ChatMessageId getId() {
		return id;
	}

	public void setId(ChatMessageId id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReciever() {
		return reciever;
	}

	public void setReciever(User reciever) {
		this.reciever = reciever;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Chat [id=" + id + ", sender=" + sender + ", reciever=" + reciever + ", message=" + message + ", date="
				+ date + ", flag=" + flag + "]";
	}
	
	
}
