package com.ikubinfo.project.entity;

import java.util.Date;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="friends",schema="socialnetwork")
public class Friends {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	
	@NotNull
	@Column(name="friend_id")
	private long friendId;
	
	@NotNull
	@Column(name="date")
	@Temporal(TemporalType.TIMESTAMP) 
	private Date date;
	
	@NotNull
	@Column(name="accepted")
	private boolean accepted;
	
	@NotNull
	@Column(name="flag")
	private boolean flag;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	public Friends() {
		
	}

	

	public long getFriendId() {
		return friendId;
	}

	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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


	
	
	
}
