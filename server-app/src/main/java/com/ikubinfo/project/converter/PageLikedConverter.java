package com.ikubinfo.project.converter;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.project.base.BaseConverter;
import com.ikubinfo.project.entity.Page;
import com.ikubinfo.project.entity.PageLiked;
import com.ikubinfo.project.model.PageLikedModel;
import com.ikubinfo.project.model.PageModel;

public class PageLikedConverter implements BaseConverter<PageLikedModel, PageLiked> {
	
	private UserConverter userConverter;
	public PageLikedConverter() {
		this.userConverter=new UserConverter();
	}

	@Override
	public PageLikedModel toModel(PageLiked entity) {
		PageLikedModel model = new PageLikedModel();
		model.setId(entity.getId());
		model.setUser(userConverter.toModel(entity.getUser()));
		model.setPage(toModel((entity.getPage())));
		model.setDate(entity.getDate());
		model.setFlag(entity.isFlag());
		return model;
	}

	@Override
	public PageLiked toEntity(PageLikedModel model) {
		PageLiked entity = new PageLiked();
		entity.setId(model.getId());
		entity.setUser(userConverter.toEntity(model.getUser()));
		entity.setPage(toEntity(model.getPage()));
		entity.setDate(model.getDate());
		entity.setFlag(model.isFlag());
		return entity;
	}
	
	public List<PageLikedModel> toModel(List<PageLiked> entityList){
		List<PageLikedModel> model = new ArrayList<PageLikedModel>();
		for (PageLiked pageLiked: entityList) {
			model.add(toModel(pageLiked));
		}
		return model;
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

}
