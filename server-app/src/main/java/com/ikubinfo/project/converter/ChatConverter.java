package com.ikubinfo.project.converter;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.project.base.BaseConverter;
import com.ikubinfo.project.entity.Chat;
import com.ikubinfo.project.model.ChatModel;

public class ChatConverter implements BaseConverter<ChatModel,Chat> {
	private UserConverter userConverter;
	public ChatConverter() {
		this.userConverter=new UserConverter();
	}

	@Override
	public ChatModel toModel(Chat entity) {
		ChatModel model=new ChatModel();
		model.setId(entity.getId());
		model.setMessage(entity.getMessage());
		model.setSender(userConverter.toModel(entity.getSender()));
		model.setReciever(userConverter.toModel(entity.getReciever()));
		model.setDate(entity.getDate());
		model.setFlag(entity.isFlag());
		return model;
	}

	@Override
	public Chat toEntity(ChatModel model) {
		Chat entity = new Chat ();
		entity.setId(model.getId());
		entity.setSender(userConverter.toEntity(model.getSender()));
		entity.setMessage(model.getMessage());
		entity.setReciever(userConverter.toEntity(model.getReciever()));
		entity.setFlag(entity.isFlag());
		entity.setDate(entity.getDate());
		return entity;
	}
	
	public List<ChatModel> toModel(List<Chat> entityList){
		List<ChatModel> modelList=new ArrayList<ChatModel>();
		for (Chat chat: entityList) {
			modelList.add(toModel(chat));
		}
		return modelList;
	}

}
