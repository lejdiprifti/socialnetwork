package com.ikubinfo.project.model;

import java.util.Date;
import java.util.List;

import com.ikubinfo.project.entity.RoleEntity;
import com.ikubinfo.project.entity.SocialLinks;

public class UserModel {
	private long id;
	private String firstName;
	private String lastName;
	private String password;
	private Date birthdate;
	private String email;
	private RoleEntity role;
	private String job;
	private String education;
	private String address;
	private String image;
	private String bio;
	private SocialLinks socialLinks;
	private List<PostModel> posts;
	private List<UserModel> friends;
	private List<PostModel> likes;
	private boolean flag;
	


	public UserModel() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public String getJob() {
		return job;
	}

	public List<PostModel> getPosts() {
		return posts;
	}

	public void setPosts(List<PostModel> posts) {
		this.posts = posts;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<UserModel> getFriends() {
		return friends;
	}

	public void setFriends(List<UserModel> friends) {
		this.friends = friends;
	}

	public List<PostModel> getLikes() {
		return likes;
	}

	public void setLikes(List<PostModel> list) {
		this.likes = list;
	} 

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public SocialLinks getSocialLinks() {
		return socialLinks;
	}

	public void setSocialLinks(SocialLinks socialLinks) {
		this.socialLinks = socialLinks;
	}

	
		
}