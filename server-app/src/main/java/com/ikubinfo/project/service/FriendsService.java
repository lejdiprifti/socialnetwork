package com.ikubinfo.project.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.NotFoundException;

import com.ikubinfo.project.converter.FriendsConverter;
import com.ikubinfo.project.converter.UserConverter;
import com.ikubinfo.project.entity.Friends;
import com.ikubinfo.project.entity.FriendsId;
import com.ikubinfo.project.entity.User;
import com.ikubinfo.project.model.FriendsModel;
import com.ikubinfo.project.model.UserModel;
import com.ikubinfo.project.repository.FriendsRepository;
import com.ikubinfo.project.repository.UserRepository;

public class FriendsService {
	private FriendsRepository friendsRepository;
	private UserRepository userRepository;
	private FriendsConverter friendsConverter;
	private UserConverter userConverter;
	public FriendsService() {
		this.friendsRepository=new FriendsRepository();
		this.userRepository=new UserRepository();
		this.friendsConverter=new FriendsConverter();
		this.userConverter=new UserConverter();
	}
	
	public List<FriendsModel> getRequests(String email) {
		User user=userRepository.getUserByEmail(email);
		return friendsConverter.toModel(friendsRepository.getRequests(user));
	}
	
	public List<FriendsModel> getMyRequests(final long id){
		User user=userRepository.getUserById(id);
		return friendsConverter.toModel(friendsRepository.getMyRequests(user));
	}
	public void acceptFriendRequest(final long id,String email) {
		try {
			FriendsId friendsId=new FriendsId();
			friendsId.setFriendId(userRepository.getUserByEmail(email).getId());
			friendsId.setUserId(id);
		Friends friends=friendsRepository.getRequest(friendsId);
		friends.setAccepted(true);
		friendsRepository.acceptFriendRequest(friends);
		}catch(NoResultException e) {
			throw new NotFoundException("Request not found.");
		}
	}

	
	public void rejectFriendRequest(final long id,String email) {
		try {
		FriendsId friendsId = new FriendsId();
		friendsId.setFriendId(userRepository.getUserByEmail(email).getId());
		friendsId.setUserId(id);
		Friends friends=friendsRepository.getRequest(friendsId);
		friends.setAccepted(false);
		friends.setFlag(false);
		friendsRepository.acceptFriendRequest(friends);
		}catch(NoResultException e) {
			throw new NotFoundException("Request not found.");
		}
	}

	public void cancelRequest(final long id,String email) {
		try {
			FriendsId friendsId= new FriendsId();
			friendsId.setFriendId(id);
			friendsId.setUserId(userRepository.getUserByEmail(email).getId());
			Friends friends=friendsRepository.getRequest(friendsId);
			friends.setFlag(false);
			friendsRepository.acceptFriendRequest(friends);
		}catch(NoResultException e) {
			throw new NotFoundException("Request not found.");
		}
	}
	
	public List<UserModel> getFriends(String email){
		try {
			User user=userRepository.getUserByEmail(email);
			return userConverter.toModel(friendsRepository.getFriends(user));
		}catch(NoResultException e) {
			throw new NotFoundException();
		}
	}
	
}

	