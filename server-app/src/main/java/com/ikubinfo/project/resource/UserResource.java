package com.ikubinfo.project.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.Response;

import com.ikubinfo.project.base.BaseResource;
import com.ikubinfo.project.model.UserModel;
import com.ikubinfo.project.service.UserService;
import com.ikubinfo.project.util.Paths;

@Path(Paths.USERS)
public class UserResource extends BaseResource {
	private UserService userService;

	public UserResource() {
		this.userService = new UserService();
	}

	@GET
	@Path("/{id}")
	public Response getUserById(@PathParam("id") final int id) {
		return Response.ok(userService.getUserById(id)).build();
	}
	
	
}
