package com.ikubinfo.project.service;

import java.util.Date;

import javax.persistence.NoResultException;
import javax.ws.rs.NotFoundException;

import com.ikubinfo.project.entity.Friends;
import com.ikubinfo.project.entity.User;
import com.ikubinfo.project.repository.FriendsRepository;
import com.ikubinfo.project.repository.UserRepository;

public class FriendsService {
	private FriendsRepository friendsRepository;
	private UserRepository userRepository;
	public FriendsService() {
		this.friendsRepository=new FriendsRepository();
		this.userRepository=new UserRepository();
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
}
