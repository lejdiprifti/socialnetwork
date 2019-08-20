package com.ikubinfo.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pages", schema = "socialnetwork")
public class Page {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(updatable = false, nullable = false)
	private long id;

	@Column(length = 50)
	private String name;

	@Column(name = "biography", length = 1000)
	private String bio;

	@ManyToOne
	@Column(name = "createdBy")
	private User user;

	@ManyToOne
	@Column(name = "role_id")
	private RoleEntity role;

	private boolean flag;

	public Page() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Page [id=" + id + ", name=" + name + ", bio=" + bio + ", user=" + user + ", role=" + role + ", flag="
				+ flag + "]";
	}

}
