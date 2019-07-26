package com.ikubinfo.project.converter;

import com.ikubinfo.project.model.*;
import com.ikubinfo.project.base.BaseConverter;
import com.ikubinfo.project.entity.User;

public class UserConverter implements BaseConverter<UserModel, User> {

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
		entity.setJob(model.getJob());
		return entity;
	}

}
