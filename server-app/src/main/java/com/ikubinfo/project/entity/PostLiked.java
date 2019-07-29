package com.ikubinfo.project.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="postliked",schema="socialnetwork")
public class PostLiked {
	
	@EmbeddedId
	private PostLikedId id;
	
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name="userid")
	private User user;
	
	@ManyToOne
	@MapsId("postId")
	@JoinColumn(name="postid")
	private Post post;
	
	@Column(name="updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	private boolean flag;
	
	public PostLiked() {
		
	}

	public PostLikedId getId() {
		return id;
	}

	public void setId(PostLikedId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
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

	@Override
	public String toString() {
		return "PostLiked [id=" + id + ", user=" + user + ", post=" + post + ", date=" + date + ", flag=" + flag + "]";
	}


	
		
}
