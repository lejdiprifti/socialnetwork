package com.ikubinfo.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="chat",schema="socialnetwork")
@NamedQueries({
	@NamedQuery(name="Chat.getMessages",query="Select c from Chat c where ( c.user=?1 and c.reciever=?2) or (c.user=?2 and c.reciever=?1) and c.flag=?3"),
	@NamedQuery(name="Chat.getMessageById", query="Select c from Chat c where c.id=?1 and c.flag=?2")
})
public class Chat {

	public Chat() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="message_id",nullable=false, unique=true)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="reciever_id")
	private User reciever;
	
	@Column(name="message",nullable=false,length=1000000)
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date")
	private Date date;
	
	@Column(name="flag")
	private boolean flag;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		return "Chat [id=" + id + ", user=" + user + ", reciever=" + reciever + ", message=" + message + ", date="
				+ date + ", flag=" + flag + "]";
	}
	
	
}
