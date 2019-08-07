package com.ikubinfo.project.resource;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.ikubinfo.project.base.BaseResource;
import com.ikubinfo.project.model.ChatModel;
import com.ikubinfo.project.service.ChatService;
import com.ikubinfo.project.util.Paths;
@Path(Paths.CHAT)
public class ChatResource extends BaseResource {
	private ChatService chatService;
	public ChatResource() {
		this.chatService=new ChatService();
	}
	
	@GET
	@Path("/{id}")
	public Response getMessages(@PathParam("id") final long id) {
		return Response.ok(chatService.getMessages(getEmailFromToken(), id)).build();
	}
	@POST
	@Path("/{id}")
	public Response sendMessage(@PathParam("id") final long id,ChatModel chatModel) throws URISyntaxException {
		chatService.sendMessage(chatModel, id, getEmailFromToken());
		return Response.created(new URI("/messages/"+id)).build();
	}
}
