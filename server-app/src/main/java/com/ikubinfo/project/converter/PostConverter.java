package com.ikubinfo.project.converter;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.project.base.BaseConverter;
import com.ikubinfo.project.entity.Post;
import com.ikubinfo.project.model.PostModel;

public class PostConverter implements BaseConverter<PostModel,Post> {
	private UserConverter userConverter;
	private PostLikedConverter postLikedConverter;
	private PageConverter pageConverter;
	public PostConverter() {
		this.userConverter=new UserConverter();
		this.postLikedConverter=new PostLikedConverter(userConverter);
		this.pageConverter=new PageConverter();
	}

	@Override
	public PostModel toModel(Post entity) {
		PostModel model=new PostModel();
		model.setId(entity.getId());
		model.setTitle(entity.getTitle());
		model.setDescription(entity.getDescription());
		model.setDate(entity.getDate());
		model.setUser(userConverter.toModel(entity.getUser()));
		model.setFlag(entity.isFlag());
		if (entity.getUser() != null)
		model.setLikes(postLikedConverter.toModel(entity.getLikes()));
		if (entity.getPage() != null)
		model.setPage(pageConverter.toModel(entity.getPage()));
		return model;
		
	}

	@Override
	public Post toEntity(PostModel model) {
		Post entity=new Post();
		entity.setId(model.getId());
		entity.setTitle(model.getTitle());
		entity.setDescription(model.getDescription());
		entity.setDate(model.getDate());
		if (model.getUser() != null)
		entity.setUser(userConverter.toEntity(model.getUser()));
		entity.setFlag(model.isFlag());
		if (model.getLikes() != null)
		entity.setLikes(postLikedConverter.toEntity(model.getLikes()));
		if (model.getPage() != null)
		entity.setPage(pageConverter.toEntity(model.getPage()));
		return entity;
	}
	
	public List<PostModel> toModel(List<Post> entityList){
		List<PostModel> modelList=new ArrayList<PostModel>();
		for (Post post: entityList) {
			modelList.add(toModel(post));
		}
		return modelList;
	}

}
