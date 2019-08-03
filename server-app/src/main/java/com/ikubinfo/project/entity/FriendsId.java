package com.ikubinfo.project.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FriendsId implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FriendsId() {
		
	}
	
	@Column(name="user_id")
	private long userId;
	
	@Column(name="friend_id")
	private long friendId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getFriendId() {
		return friendId;
	}

	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (friendId ^ (friendId >>> 32));
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FriendsId other = (FriendsId) obj;
		if (friendId != other.friendId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	
	
}
