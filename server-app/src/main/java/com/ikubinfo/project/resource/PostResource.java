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
import com.ikubinfo.project.model.PostModel;
import com.ikubinfo.project.service.PostService;
import com.ikubinfo.project.util.Paths;
@Path(Paths.POSTS)
public class PostResource extends BaseResource {
	private PostService postService;
	public PostResource() {
		this.postService= new PostService();
	}

	@GET
	public Response getPosts() {
		return ok(postService.getPosts());
	}
	
	@GET
	@Path("/{id}")
	public Response getPostById(@PathParam("id") final long id) {
		return ok(postService.getPostById(id));
	}
	
	@POST
	public Response addPost(PostModel post) throws URISyntaxException {
		return Response.created(new URI("/post/"+postService.addPost(post, getEmailFromToken()).getId())).build();
	}
	
	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") final long id,PostModel post) {
		return Response.ok(postService.update(id, post)).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") final long id) {
		postService.delete(id);
		return Response.noContent().build();
	}
	
	@PUT
	@Path("/{id}/like")
	public Response like(@PathParam("id") final long id) {
		postService.like(id, getEmailFromToken());
		return Response.noContent().build();
	}
	
	@PUT
	@Path("/{id}/unlike")
	public Response unlike(@PathParam("id") final long id) { 
				postService.unlike(id, getEmailFromToken());
				return Response.noContent().build();
	}
}
