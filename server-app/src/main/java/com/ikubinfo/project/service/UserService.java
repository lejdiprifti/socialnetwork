package com.ikubinfo.project.service;

import javax.persistence.NoResultException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import com.ikubinfo.project.converter.UserConverter;
import com.ikubinfo.project.entity.RoleEntity;
import com.ikubinfo.project.model.UserModel;
import com.ikubinfo.project.repository.UserRepository;

public class UserService {
	private UserRepository userRepository;
	private UserConverter userConverter;

	public UserService() {
		userRepository = new UserRepository();
		userConverter = new UserConverter();
	}

	public UserModel getUserById(long id) {
		try {
			return userConverter.toModel(userRepository.getUserById(id));
		}catch(NoResultException e) {
			throw new NotFoundException("User not found");
		}
	}

	public UserModel getUserByEmail(String email) {
		try {
			return userConverter.toModel(userRepository.getUserByEmail(email));
		}catch(NoResultException e) {
			throw new NotFoundException("User not found");
		}
	}

	public UserModel register(UserModel user) {
		try {
			userRepository.getUserByEmail(user.getEmail());
			throw new BadRequestException("User already exists.");
		}catch(NoResultException e) {
			RoleEntity role=new RoleEntity();
			role.setId(2);
			user.setFlag(true);
			user.setRole(role);
			return userConverter.toModel(userRepository.register(userConverter.toEntity(user)));
		}
	}

	
}
