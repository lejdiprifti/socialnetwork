package com.ikubinfo.project.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.ikubinfo.project.base.BaseResource;
import com.ikubinfo.project.service.FriendsService;
import com.ikubinfo.project.util.Paths;
@Path(Paths.REQUESTS)
public class FriendsResource extends BaseResource {
	private FriendsService friendsService;
	public FriendsResource()  {
		this.friendsService=new FriendsService();
	}
	
	@GET
	public Response getRequests() {
		return Response.ok(friendsService.getRequests(getEmailFromToken())).build();
	}
	
	@GET
	@Path("/friends")
	public Response getFriends() {
		return Response.ok(friendsService.getFriends(getEmailFromToken())).build();
	}
	
	@GET
	@Path("/{id}")
	public Response getRequestsOfUser(@PathParam("id") final long id){
		return Response.ok(friendsService.getMyRequests(id)).build();
	}

	@PUT
	@Path("/{id}/accept")
	public Response acceptFriendRequest(@PathParam("id") final long id) {
		friendsService.acceptFriendRequest(id,getEmailFromToken());
		return Response.noContent().build();
	}
	
	@PUT
	@Path("/{id}/reject")
	public Response rejectFriendRequest(@PathParam("id") final long id) {
		friendsService.rejectFriendRequest(id,getEmailFromToken());
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}/cancel")
	public Response cancelFriendRequest(@PathParam("id") final long id) {
		friendsService.cancelRequest(id, getEmailFromToken());
		return Response.noContent().build();
	}
	
}
