package com.ikubinfo.project.service;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import com.ikubinfo.project.converter.UserConverter;
import com.ikubinfo.project.entity.Friends;
import com.ikubinfo.project.entity.RoleEntity;
import com.ikubinfo.project.entity.User;
import com.ikubinfo.project.model.UserModel;
import com.ikubinfo.project.repository.UserRepository;

public class UserService {
	private UserRepository userRepository;
	private UserConverter userConverter;

	public UserService() {
		userRepository = new UserRepository();
		userConverter = new UserConverter();
	}
	
	public List<UserModel> getAllUsers(){
		return userConverter.toModel(userRepository.getAllUsers());
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
	
	public UserModel update(UserModel user,long id) {
		try {
		User foundUser=userRepository.getUserById(id);
		user.setId(id);
		if (user.getFirstName() == null) {
			user.setFirstName(foundUser.getFirstName());
		}
		if (user.getLastName() == null) {
			user.setLastName(foundUser.getLastName());
		}
		user.setEmail(foundUser.getEmail());
		if (user.getBirthdate()==null) {
			user.setBirthdate(foundUser.getBirthdate());
		}
		if (user.getEducation()==null) {
			user.setEducation(foundUser.getEducation());
		}
		if (user.getAddress()==null) {
			user.setAddress(foundUser.getAddress());
		}
		if (user.getJob()==null) {
			user.setJob(foundUser.getJob());
		}
		if (user.getPassword()==null) {
			user.setPassword(foundUser.getPassword());
		}
		user.setFlag(true);
		userRepository.update(userConverter.toEntity(user));
		return user;
		}catch(NoResultException e) {
			throw new NotFoundException("User not found.");
		}
	}
	
	public void delete(long id) {
		try {
		User user=userRepository.getUserById(id);
		user.setFlag(false);
		userRepository.update(user);
		}catch(NoResultException e) {
			throw new NotFoundException("User not found.");
		}
	}
	
	public Friends addFriend(final long id,String email) {
		if (existsUser(id)==true) {
		User user=userRepository.getUserByEmail(email);
		Friends friend=new Friends();
		friend.setFriend(userRepository.getUserById(id));
		friend.setFlag(true);
		friend.setAccepted(false);
		friend.setDate(new Date());
		friend.setUser(user);
		userRepository.addFriend(friend);
		return friend;
		}else {
			throw new BadRequestException("User not found.");
		}
	}
	
	
	
	public boolean existsUser(final long id) {
		try {
		userRepository.getUserById(id);
		return true;
		}catch(NoResultException e) {
			return false;
		}
	}
}

