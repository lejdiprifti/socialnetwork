package com.ikubinfo.project.converter;

import com.ikubinfo.project.model.*;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.project.base.BaseConverter;
import com.ikubinfo.project.entity.User;

public class UserConverter implements BaseConverter<UserModel, User> {

	public UserConverter() {

	}
	@Override
	public UserModel toModel(User entity) {
		UserModel model = new UserModel();
		model.setId(entity.getId());
		model.setFirstName(entity.getFirstName());
		model.setLastName(entity.getLastName());
		model.setEmail(entity.getEmail());
		model.setPassword(entity.getPassword()); 
		model.setRole(entity.getRole());
		model.setEducation(entity.getEducation());
		model.setAddress(entity.getAddress());
		model.setFlag(entity.isFlag());
		model.setBirthdate(entity.getBirthdate());
		model.setImage(entity.getImage());
		model.setBio(entity.getBio());
		model.setSocialLinks(entity.getSocialLinks());
		model.setJob(entity.getJob());
		return model;
	}

	@Override
	public User toEntity(UserModel model) {
		User entity = new User();
		entity.setId(model.getId());
		entity.setFirstName(model.getFirstName());
		entity.setLastName(model.getLastName());
		entity.setEmail(model.getEmail());
		entity.setEducation(model.getEducation());
		entity.setAddress(model.getAddress());
		entity.setPassword(model.getPassword());
		entity.setRole(model.getRole());
		entity.setFlag(model.isFlag());
		entity.setBirthdate(model.getBirthdate());
		entity.setImage(model.getImage());
		entity.setBio(model.getBio());
		entity.setSocialLinks(model.getSocialLinks());
		entity.setJob(model.getJob());
		return entity;
	}
	
	public List<UserModel> toModel(List<User> userList){
		List<UserModel> modelList=new ArrayList<UserModel>();
		for (User user: userList) {
			modelList.add(toModel(user));
		}
		return modelList;
	}

}
