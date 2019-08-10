package com.ikubinfo.project.resource;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.Response;

import com.ikubinfo.project.base.BaseResource;
import com.ikubinfo.project.model.UserModel;
import com.ikubinfo.project.service.PostService;
import com.ikubinfo.project.service.UserService;
import com.ikubinfo.project.util.Paths;

@Path(Paths.USERS)
public class UserResource extends BaseResource {
	private UserService userService;
	private PostService postService;
	
	public UserResource() {
		this.userService = new UserService();
		this.postService = new PostService();
	}
	
	@GET
	public Response getUsers() {
		return Response.ok(userService.getAllUsers(getEmailFromToken())).build();
	}
	
	@GET
	@Path("/{id}")
	public Response getUserById(@PathParam("id") final long id) {
		return Response.ok(userService.getUserById(id)).build();
	}
	
	@GET
	@Path("/{id}/posts")
	public Response getPostsOfUser(@PathParam("id") final long id) {
		return Response.ok(postService.getMyPosts(id)).build();
	}
	
	@PUT 
	@Path("/{id}")
	public Response update(@PathParam("id") final long id,UserModel user) {
		return Response.ok(userService.update(user, id)).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") final long id) {
		userService.delete(id);
		return Response.noContent().build();
	}
	
	@POST
	@Path("/{id}")
	public Response addFriend(@PathParam("id") final long id) throws URISyntaxException {
		return Response.created(new URI("friend/"+userService.addFriend(id, getEmailFromToken()).getId())).build();
	}
	
	
}
