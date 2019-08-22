package com.ikubinfo.project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import com.ikubinfo.project.converter.PostConverter;
import com.ikubinfo.project.converter.UserConverter;
import com.ikubinfo.project.entity.Post;
import com.ikubinfo.project.entity.PostLiked;
import com.ikubinfo.project.entity.PostLikedId;
import com.ikubinfo.project.entity.User;
import com.ikubinfo.project.model.PageModel;
import com.ikubinfo.project.model.PostModel;
import com.ikubinfo.project.repository.PostRepository;
import com.ikubinfo.project.repository.UserRepository;

public class PostService {
	private PostRepository postRepository;
	private PostConverter postConverter;
	private UserRepository userRepository;
	private UserConverter userConverter;
	private PageService pageService;
	public PostService() {
		this.postConverter=new PostConverter();
		this.postRepository=new PostRepository();
		this.userRepository=new UserRepository();
		this.userConverter=new UserConverter();
		this.pageService=new PageService();
	}
	
	public List<PostModel> getPosts(String email){
		return postConverter.toModel(postRepository.getPosts(userRepository.getUserByEmail(email)));
	}
	
	public PostModel getPostById(final long id) {
		try {
			return postConverter.toModel(postRepository.getPostById(id));
		}catch(NoResultException e) {
			throw new NotFoundException("Post not found.");
		}
	}
	
	public PostModel addPost(PostModel post,String email) {
		try {
			post.setUser(userConverter.toModel(userRepository.getUserByEmail(email)));
			post.setDate(new Date());
			post.setPage(null);
			post.setFlag(true);
			post.setDescription(post.getDescription());
			post.setTitle(post.getTitle());
			post.setLikes(new ArrayList<>());
			return postConverter.toModel(postRepository.addPost(postConverter.toEntity(post)));
		} catch (NullPointerException e) {
			throw new BadRequestException("Null values");
		}
	} 
	public PostModel update(final long id,PostModel post) {
		try {
		Post foundPost = postRepository.getPostById(id);
		if (post.getTitle()!=null) {
			foundPost.setTitle(post.getTitle());
		}
		if (post.getDescription()!=null) {
			foundPost.setDescription(post.getDescription());
		}
		postRepository.update(foundPost);
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
	
	public void like(final long id,String email) {
		try {
		Post post=postRepository.getPostById(id);
		User user=userRepository.getUserByEmail(email);
		PostLiked postLiked=new PostLiked();
		postLiked.setDate(new Date());
		postLiked.setFlag(true);
		postLiked.setPost(post);
		postLiked.setUser(user);
		PostLikedId postLikedId=new PostLikedId();
		postLikedId.setUserId(user.getId());
		postLikedId.setPostId(post.getId());
		postLiked.setId(postLikedId);
		post.getLikes().add(postLiked);
		user.getLikedPosts().add(postLiked);
		postRepository.update(post);
		}catch(NoResultException e) {
			throw new NotFoundException("Post not found.");
		}
	} 
	
	public void unlike(final long id,String email) {
		try {
		Post post=postRepository.getPostById(id);
		User user=userRepository.getUserByEmail(email);
		PostLiked postLiked=new PostLiked();
		postLiked.setDate(new Date());
		postLiked.setFlag(false);
		postLiked.setPost(post);
		postLiked.setUser(user);
		PostLikedId postLikedId=new PostLikedId();
		postLikedId.setPostId(post.getId());
		postLikedId.setUserId(user.getId());
		postLiked.setId(postLikedId);
		post.getLikes().add(postLiked);
		user.getLikedPosts().add(postLiked);
		postRepository.update(post);
		
		}catch(NoResultException e) {
			throw new NotFoundException("Post not found.");
		}
	}
	
	public boolean isAlreadyLiked(Post post,User user) {
		try {
			postRepository.isAlreadyLiked(post, user);
			return true;
		}catch(NoResultException e) {
			return false;
		}
	}
	
	public List<PostModel> getMyPosts(User user) {
		try {
			return postConverter.toModel(postRepository.getMyPosts(user));
		}catch(NoResultException e) {
			throw new NotFoundException();
		}
	}
	
	public List<PostModel> getMyLikes(User user){
			return postConverter.toModel(postRepository.getMyLikes(user));
	}
	
	public Post addPost(PostModel post, final long pageId,final String email) {
		PageModel page=pageService.getPageById(pageId);
		post.setPage(page);
		post.setDate(new Date());
		post.setFlag(true);
		post.setUser(userConverter.toModel(userRepository.getUserByEmail(email)));
		return postRepository.addPost(postConverter.toEntity(post));
	}
}
