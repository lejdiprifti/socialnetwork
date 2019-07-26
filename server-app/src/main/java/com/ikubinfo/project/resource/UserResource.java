package com.ikubinfo.project.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ikubinfo.project.service.UserService;
import com.ikubinfo.project.util.Paths;

@Path(Paths.USERS)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	private UserService userService;
	
	public UserResource() {
		this.userService= new UserService();
	}
	
	@GET
	@Path("/{id}")
	public Response getUserById(@PathParam("id") final int id) {
		return Response.ok((userService.getUser(id))).build();
	}
}
