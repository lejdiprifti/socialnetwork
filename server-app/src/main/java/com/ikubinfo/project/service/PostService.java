package com.ikubinfo.project.service;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.NotFoundException;

import com.ikubinfo.project.converter.PostConverter;
import com.ikubinfo.project.entity.Post;
import com.ikubinfo.project.model.PostModel;
import com.ikubinfo.project.repository.PostRepository;
import com.ikubinfo.project.repository.UserRepository;

public class PostService {
	private PostRepository postRepository;
	private PostConverter postConverter;
	private UserRepository userRepository;
	
	public PostService() {
		this.postConverter=new PostConverter();
		this.postRepository=new PostRepository();
		this.userRepository=new UserRepository();
	}
	
	public List<PostModel> getPosts(){
		return postConverter.toModel(postRepository.getPosts());
	}
	
	public PostModel getPostById(final long id) {
		try {
			return postConverter.toModel(postRepository.getPostById(id));
		}catch(NoResultException e) {
			throw new NotFoundException("Post not found.");
		}
	}
	
	public PostModel addPost(PostModel post,String email) {
			post.setUser(userRepository.getUserByEmail(email));
			post.setDate(new Date());
			post.setFlag(true);
			return postConverter.toModel(postRepository.addPost(postConverter.toEntity(post)));
	}
	public PostModel update(final long id,PostModel post) {
		try {
		Post foundPost = postRepository.getPostById(id);
		post.setId(foundPost.getId());
		if (post.getTitle()==null) {
			post.setTitle(foundPost.getTitle());
		}
		if (post.getDescription()==null) {
			post.setDescription(foundPost.getDescription());
		}
		post.setDate(new Date());
		post.setUser(foundPost.getUser());
		post.setFlag(true);
		postRepository.update(postConverter.toEntity(post));
		return post;
	}catch(NoResultException e){
		throw new NotFoundException("Post not found.");
	}
	}
	
	public void delete(final long id) {
		try {
			Post foundPost = postRepository.getPostById(id);
			foundPost.setFlag(false);
			postRepository.update(foundPost);
		}catch(NoResultException e) {
			throw new NotFoundException("Post not found.");
		}
	}
}
