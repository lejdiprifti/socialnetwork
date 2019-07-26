package com.ikubinfo.project.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ikubinfo.project.model.UserModel;
import com.ikubinfo.project.service.UserService;
import com.ikubinfo.project.util.Paths;

@Path(Paths.REGISTER)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegisterResource {
	private UserService userService;

	public RegisterResource() {
		this.userService = new UserService();
	}

	@POST
	public Response register(UserModel user) {
		return Response.ok(userService.register(user)).build();
	}
}
