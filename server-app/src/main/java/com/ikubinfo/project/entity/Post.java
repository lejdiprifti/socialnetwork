package com.ikubinfo.project.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.NaturalIdCache;

@Entity
@Table(name="post",schema="socialnetwork")
@NamedQueries({
	@NamedQuery(name="Post.getPosts",query="Select p from Post p where p.flag=?1"),
	@NamedQuery(name="Post.getPostById",query="Select p from Post p where p.id=?1 and p.flag=?2")
	
})
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="post_id",nullable=false,unique=true)
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(
	        mappedBy = "post",
	        cascade = CascadeType.ALL,
	        fetch=FetchType.LAZY,
	        orphanRemoval = true
	    )
	    private List<PostLiked> likes = new ArrayList<>();
	@Column(name="flag")
	private boolean flag;
	
	
	public Post() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	public List<PostLiked> getLikes() {
		return likes;
	}

	public void setLikes(List<PostLiked> likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + ", date=" + date + ", user=" + user + ", flag="
				+ flag + "]";
	}
	
	
}
