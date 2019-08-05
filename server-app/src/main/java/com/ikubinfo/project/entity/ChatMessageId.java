package com.ikubinfo.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ChatMessageId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChatMessageId() {

	}
	
	@Column(name="sender_id")
	private long senderId;
	
	@Column(name="reciever_id")
	private long recieverId;

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public long getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(long recieverId) {
		this.recieverId = recieverId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (recieverId ^ (recieverId >>> 32));
		result = prime * result + (int) (senderId ^ (senderId >>> 32));
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
		ChatMessageId other = (ChatMessageId) obj;
		if (recieverId != other.recieverId)
			return false;
		if (senderId != other.senderId)
			return false;
		return true;
	}
	
	
	
	

}
