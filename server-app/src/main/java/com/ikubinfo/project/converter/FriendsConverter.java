package com.ikubinfo.project.converter;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.project.base.BaseConverter;
import com.ikubinfo.project.entity.Friends;
import com.ikubinfo.project.model.FriendsModel;

public class FriendsConverter implements BaseConverter<FriendsModel,Friends>{

	public FriendsConverter() {
		
	}

	@Override
	public FriendsModel toModel(Friends entity) {
		FriendsModel model=new FriendsModel();
		model.setId(entity.getId());
		model.setFriendId(entity.getFriendId());
		model.setUser(entity.getUser());
		model.setAccepted(entity.isAccepted());
		model.setDate(entity.getDate());
		model.setFlag(entity.isFlag());
		return model;
	}

	@Override
	public Friends toEntity(FriendsModel model) {
		Friends entity = new Friends();
		entity.setId(model.getId());
		entity.setFriendId(model.getFriendId());
		entity.setUser(model.getUser());
		entity.setDate(model.getDate());
		entity.setAccepted(model.isAccepted());
		entity.setFlag(model.isFlag());
		return entity;
	}
	
	public List<FriendsModel> toModel(List<Friends> entityList){
		List<FriendsModel> modelList=new ArrayList<FriendsModel>();
		for (Friends friends: entityList) {
			modelList.add(toModel(friends));
		}
		return modelList;
	}
}
