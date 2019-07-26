package com.ikubinfo.project.converter;

import com.ikubinfo.project.model.*;
import com.ikubinfo.project.base.BaseConverter;
import com.ikubinfo.project.entity.*;
import com.ikubinfo.project.entity.User;

public class UserConverter implements BaseConverter<UserModel, User> {

	@Override
	public UserModel toModel(User entity) {
		UserModel model = new UserModel();
		model.setId(entity.getId());
		model.setUsername(entity.getUsername());
		model.setPassword(entity.getPassword());
		model.setRole(entity.getRole());
		model.setAddress(entity.getAddress());
		model.setBirthdate(entity.getBirthdate());
		model.setEmail(entity.getEmail());
		model.setFlag(entity.isFlag());
		return model;
	}

	@Override
	public User toEntity(UserModel model) {
		// TODO Auto-generated method stub
		User entity = new User();
		entity.setId(model.getId());
		entity.setUsername(model.getUsername());
		entity.setPassword(model.getPassword());
		entity.setRole(model.getRole());
		entity.setAddress(model.getAddress());
		entity.setBirthdate(model.getBirthdate());
		entity.setEmail(model.getEmail());
		entity.setFlag(model.isFlag());
		return entity;
	}

}
