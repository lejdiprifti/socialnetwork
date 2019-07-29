package com.ikubinfo.project.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.NotFoundException;

import com.ikubinfo.project.converter.FriendsConverter;
import com.ikubinfo.project.entity.Friends;
import com.ikubinfo.project.entity.User;
import com.ikubinfo.project.model.FriendsModel;
import com.ikubinfo.project.repository.FriendsRepository;
import com.ikubinfo.project.repository.UserRepository;

public class FriendsService {
	private FriendsRepository friendsRepository;
	private UserRepository userRepository;
	private FriendsConverter friendsConverter;
	public FriendsService() {
		this.friendsRepository=new FriendsRepository();
		this.userRepository=new UserRepository();
		this.friendsConverter=new FriendsConverter();
	}
	
	public List<FriendsModel> getRequests(String email) {
		User user=userRepository.getUserByEmail(email);
		return friendsConverter.toModel(friendsRepository.getRequests(user));
	}
	
	public void acceptFriendRequest(final long id) {
		try {
		Friends friends=friendsRepository.getRequest(id);
		friends.setAccepted(true);
		friendsRepository.acceptFriendRequest(friends);
		}catch(NoResultException e) {
			throw new NotFoundException("Request not found.");
		}
	}

	
	public void rejectFriendRequest(final long id) {
		try {
		Friends friends=friendsRepository.getRequest(id);
		friends.setFlag(false);
		friendsRepository.acceptFriendRequest(friends);
		}catch(NoResultException e) {
			throw new NotFoundException("Request not found.");
		}
	}
	
	public void deleteRequest(final long id) {
		try {
			Friends friends=friendsRepository.getRequest(id);
			friends.setFlag(false);
			friendsRepository.deleteRequest(friends);
		}catch(NoResultException e) {
			throw new NotFoundException("Request not found.");
		}
	}
	
	
}
