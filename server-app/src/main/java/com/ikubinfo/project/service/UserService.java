package com.ikubinfo.project.service;

import com.ikubinfo.project.converter.UserConverter;
import com.ikubinfo.project.model.UserModel;
import com.ikubinfo.project.repository.UserRepository;

public class UserService {
	private UserRepository userRepository;
	private UserConverter userConverter;

	public UserService() {
		userRepository = new UserRepository();
		userConverter = new UserConverter();
	}

	public UserModel getUser(int id) {
		return userConverter.toModel(userRepository.getUser(id));
	}

	public UserModel getUserByUsername(String username) {
		return userConverter.toModel(userRepository.getUserByUsername(username));
	}
}
