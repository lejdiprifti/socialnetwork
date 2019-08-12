package com.ikubinfo.project.service;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import com.ikubinfo.project.converter.UserConverter;
import com.ikubinfo.project.entity.Friends;
import com.ikubinfo.project.entity.FriendsId;
import com.ikubinfo.project.entity.RoleEntity;
import com.ikubinfo.project.entity.User;
import com.ikubinfo.project.model.UserModel;
import com.ikubinfo.project.repository.FriendsRepository;
import com.ikubinfo.project.repository.UserRepository;

public class UserService {
	private UserRepository userRepository;
	private UserConverter userConverter;
	private PostService postService;
	private FriendsRepository friendsRepository;
	public UserService() {
		userRepository = new UserRepository();
		userConverter = new UserConverter();
		this.postService=new PostService();
		this.friendsRepository=new FriendsRepository();
		}
	
	public List<UserModel> getAllUsers(String email){
		return userConverter.toModel(userRepository.getAllUsers(userRepository.getUserByEmail(email)));
	}
	
	public UserModel getUserById(long id) {
		try {
			User entity=userRepository.getUserById(id);
			UserModel user=userConverter.toModel(entity);
			user.setPosts(postService.getMyPosts(entity));
			user.setFriends(userConverter.toModel(friendsRepository.getFriends(entity)));
			user.setLikes(postService.getMyLikes(entity));
			return user;
		}catch(NoResultException e) {
			throw new NotFoundException("User not found");
		}
	}

	public UserModel getUserByEmail(String email) {
		try {
			User user=userRepository.getUserByEmail(email);
			UserModel model=userConverter.toModel(user);

			return model;
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
		if (user.getFirstName() != null) {
			foundUser.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			user.setLastName(foundUser.getLastName());
		}
			foundUser.setBirthdate(user.getBirthdate());
		if (user.getEducation()!=null) {
			foundUser.setEducation(user.getEducation());
		}
		if (user.getAddress()!=null) {
			foundUser.setAddress(user.getAddress());
		}
		if (user.getJob()!=null) {
			foundUser.setJob(user.getJob());
		}
		if (user.getPassword()!=null) {
			foundUser.setPassword(user.getPassword());
		}
		foundUser.setBio(user.getBio());
		foundUser.setSocialLinks(user.getSocialLinks());
		userRepository.update(foundUser);
		return userConverter.toModel(foundUser);
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
		FriendsId friendsId=new FriendsId();
		friendsId.setFriendId(id);
		friendsId.setUserId(user.getId());
		friend.setId(friendsId);
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

