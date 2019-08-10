package com.ikubinfo.project.service;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import com.ikubinfo.project.converter.ChatConverter;
import com.ikubinfo.project.entity.Chat;
import com.ikubinfo.project.entity.User;
import com.ikubinfo.project.model.ChatModel;
import com.ikubinfo.project.repository.ChatRepository;
import com.ikubinfo.project.repository.FriendsRepository;
import com.ikubinfo.project.repository.UserRepository;

public class ChatService {
	private ChatRepository chatRepository;
	private ChatConverter chatConverter;
	private UserRepository userRepository;
	private FriendsRepository friendsRepository;
	
	public ChatService() {
		this.chatRepository = new ChatRepository();
		this.chatConverter = new ChatConverter();
		this.userRepository = new UserRepository();
		this.friendsRepository=new FriendsRepository();
	}

	public List<ChatModel> getMessages(String email, final long id) {
		try {
			User user = userRepository.getUserByEmail(email);
			User reciever = userRepository.getUserById(id);
			friendsRepository.areFriends(reciever, user);
			return chatConverter.toModel(chatRepository.getMessages(user, reciever));
		} catch (NoResultException e) {
			throw new BadRequestException("Users are not friends.");
		}
	}

	public ChatModel getMessageById(final long id) {
		try {
			Chat chat = chatRepository.getMessageById(id);
			return chatConverter.toModel(chat);
		} catch (NoResultException e) {
			throw new NotFoundException();
		}
	}

	public void sendMessage(ChatModel chatModel, final long id, String email) {
		Chat chat = new Chat();
		chat.setDate(new Date());
		chat.setFlag(true);
		chat.setReciever(userRepository.getUserById(id));
		chat.setUser(userRepository.getUserByEmail(email));
		chat.setMessage(chatModel.getMessage());
		chatRepository.sendMessage(chat);
	}

	public void deleteMessage(final long id) {
		try {
			Chat chat = chatRepository.getMessageById(id);
			chatRepository.deleteMessage(chat);
		} catch (NoResultException e) {
			throw new NotFoundException();
		}
	}
}
