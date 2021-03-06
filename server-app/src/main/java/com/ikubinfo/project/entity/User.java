package com.ikubinfo.project.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "socialnetwork")
@NamedQueries({ @NamedQuery(name = "User.getById", query = "Select u from User u where u.id=?1 and u.flag=?2"),
		@NamedQuery(name = "User.getByEmail", query = "Select u from User u where u.email=?1 and u.flag=?2") })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false, unique = true, length = 11)
	private long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "password")
	private String password;

	@Column(name = "birthdate")
	@Temporal(TemporalType.DATE)
	private Date birthdate;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "job")
	private String job;

	@Column(name = "education")
	private String education;

	@ManyToOne
	private RoleEntity role;

	@Column(name = "address")
	private String address;

	@Column(name = "bio", length = 300)
	private String bio;

	@Embedded
	private SocialLinks socialLinks;

	@Column(name = "flag")
	private boolean flag;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Post> posts = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Friends> friends = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<PostLiked> likedPosts = new ArrayList<>();

	private String image;

	public User() {

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJob() {
		return job;
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

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Friends> getFriends() {
		return friends;
	}

	public void setFriends(List<Friends> user) {
		this.friends = user;
	}

	public List<PostLiked> getLikedPosts() {
		return likedPosts;
	}

	public void setLikedPosts(List<PostLiked> likedPosts) {
		this.likedPosts = likedPosts;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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