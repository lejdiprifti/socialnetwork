package com.ikubinfo.project.converter;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.project.base.BaseConverter;
import com.ikubinfo.project.entity.Page;
import com.ikubinfo.project.entity.Post;
import com.ikubinfo.project.model.PageModel;
import com.ikubinfo.project.model.PostModel;

public class PostConverter implements BaseConverter<PostModel,Post> {
	private UserConverter userConverter;
	private PostLikedConverter postLikedConverter;
	public PostConverter() {
		this.userConverter=new UserConverter();
		this.postLikedConverter=new PostLikedConverter(userConverter);

	}

	@Override
	public PostModel toModel(Post entity) {
		PostModel model=new PostModel();
		model.setId(entity.getId());
		model.setTitle(entity.getTitle());
		model.setDescription(entity.getDescription());
		model.setDate(entity.getDate());
		if (entity.getUser() != null)
		model.setUser(userConverter.toModel(entity.getUser()));
		model.setFlag(entity.isFlag());
		if (entity.getUser() != null)
		model.setLikes(postLikedConverter.toModel(entity.getLikes()));
		if (entity.getPage() != null)
		model.setPage(toModel(entity.getPage()));
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
		entity.setPage(toEntity(model.getPage()));
		return entity;
	}
	
	public List<PostModel> toModel(List<Post> entityList){
		List<PostModel> modelList=new ArrayList<PostModel>();
		for (Post post: entityList) {
			modelList.add(toModel(post));
		}
		return modelList;
	}

	public Page toEntity(PageModel model) {
		Page entity=new Page();
		entity.setId(model.getId());
		entity.setName(model.getName());
		entity.setBio(model.getBio());
		entity.setUser(userConverter.toEntity(model.getUser()));
		entity.setRole(model.getRole());
		entity.setDate(model.getDate());
		entity.setFlag(model.isFlag());
		return entity;
	}
	
	public PageModel toModel(Page entity) {
		PageModel model = new PageModel();
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setBio(entity.getBio());
		model.setUser(userConverter.toModel(entity.getUser()));
		model.setRole(entity.getRole());
		model.setDate(entity.getDate());
		model.setFlag(entity.isFlag());
		return model;
	}
}
