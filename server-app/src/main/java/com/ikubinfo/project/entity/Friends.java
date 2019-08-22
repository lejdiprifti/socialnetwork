package com.ikubinfo.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "friends", schema = "socialnetwork")
public class Friends {
	
	@EmbeddedId
	private FriendsId id;

	@NotNull
	@ManyToOne
	@MapsId("friendId")
	private User friend;

	@NotNull
	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@NotNull
	@Column(name = "accepted")
	private boolean accepted;

	@NotNull
	@Column(name = "flag")
	private boolean flag;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	private User user;

	public Friends() {

	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
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

	public FriendsId getId() {
		return id;
	}

	public void setId(FriendsId id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}

	

	public void setUser(User user) {
		this.user = user;
	}

}
