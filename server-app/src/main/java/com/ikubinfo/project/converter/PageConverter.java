package com.ikubinfo.project.converter;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.project.base.BaseConverter;
import com.ikubinfo.project.entity.Page;
import com.ikubinfo.project.model.PageModel;

public class PageConverter implements BaseConverter<PageModel,Page> {
	private UserConverter userConverter; 
	public PageConverter() {
		this.userConverter= new UserConverter();
	}

	@Override
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

	@Override
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
	
	public List<PageModel> toModel(List<Page> entityList){
		List<PageModel> modelList=new ArrayList<PageModel>();
		for (Page page: entityList) {
			modelList.add(toModel(page));
		}
		return modelList;
	}
	

}
