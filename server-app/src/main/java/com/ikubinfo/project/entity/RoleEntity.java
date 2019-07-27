package com.ikubinfo.project.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role", schema = "socialnetwork")
@Access(AccessType.FIELD)
public class RoleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5900614894209096873L;

	@Id
	private int id;

	@Column(name = "role_name")
	private String name;

	@Column(name = "role_description")
	private String description;
	
	@OneToMany(mappedBy="role",fetch=FetchType.LAZY)
	private List<User> users; 
	
	public RoleEntity() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
