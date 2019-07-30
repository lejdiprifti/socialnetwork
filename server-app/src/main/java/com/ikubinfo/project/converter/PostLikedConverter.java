package com.ikubinfo.project.converter;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.project.base.BaseConverter;
import com.ikubinfo.project.entity.Post;
import com.ikubinfo.project.entity.PostLiked;
import com.ikubinfo.project.model.PostLikedModel;
import com.ikubinfo.project.model.PostModel;

public class PostLikedConverter implements BaseConverter<PostLikedModel,PostLiked> {

	private UserConverter userConverter;
	
	public PostLikedConverter() {
		
	}
	public PostLikedConverter(UserConverter userConverter) {
		this.userConverter=userConverter;
	}

	@Override
	public PostLikedModel toModel(PostLiked entity) {
		PostLikedModel model=new PostLikedModel();
		model.setId(entity.getId());
		model.setPost(toModelPost(entity.getPost()));
		model.setUser(userConverter.toModel(entity.getUser()));
		model.setDate(entity.getDate());
		model.setFlag(entity.isFlag());
		return model;
	}

	@Override
	public PostLiked toEntity(PostLikedModel model) {
		PostLiked entity=new PostLiked();
		entity.setId(model.getId());
		entity.setPost(toEntityPost(model.getPost()));
		entity.setUser(userConverter.toEntity(model.getUser()));
		entity.setDate(model.getDate());
		entity.setFlag(model.isFlag());
		return entity;
	}
	
	public List<PostLikedModel> toModel(List<PostLiked> entityList){
		List<PostLikedModel> modelList=new ArrayList<>();
		for (PostLiked postLiked: entityList) {
			modelList.add(toModel(postLiked));
		}
		return modelList;
		
	}
	
	public List<PostLiked> toEntity(List<PostLikedModel> modelList){
		List<PostLiked> entityList=new ArrayList<>();
		for (PostLikedModel postLiked: modelList) {
			entityList.add(toEntity(postLiked));
		}
		return entityList;
		
	}

	
	public PostModel toModelPost(Post entity) {
		PostModel model=new PostModel();
		model.setId(entity.getId());
		model.setDescription(entity.getDescription());
		model.setDate(entity.getDate());
		model.setUser(userConverter.toModel(entity.getUser()));
		model.setFlag(entity.isFlag());
		return model;
		
	}

	public Post toEntityPost(PostModel model) {
		Post entity=new Post();
		entity.setId(model.getId());
		entity.setDescription(model.getDescription());
		entity.setDate(model.getDate());
		entity.setUser(userConverter.toEntity(model.getUser()));
		entity.setFlag(model.isFlag());
		return entity;
	}
}
