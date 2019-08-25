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
import com.ikubinfo.project.model.PageModel;
import com.ikubinfo.project.service.PageService;
import com.ikubinfo.project.util.Paths;
@Path(Paths.PAGES)
public class PageResource extends BaseResource {
	
	private PageService pageService;
	public PageResource() {
		this.pageService = new PageService();
	}
	
	@GET
	public Response getMyPages() {
		return ok(pageService.getMyPages(getEmailFromToken()));
	}
	
	@GET
	@Path("/{id}")
	public Response getPageById(@PathParam("id") final long id) {
		return ok(pageService.getPageById(id));
	}
	@POST
	public Response createPage(PageModel page) throws URISyntaxException {
		return Response.created(new URI("pages/"+pageService.createPage(page, getEmailFromToken()).getId())).build();
	}
	
	@PUT
	@Path("/{id}")
	public Response editPage(@PathParam("id") final long id,PageModel page) {
		pageService.editPage(id,page);
		return Response.noContent().build();
	}
	
	@PUT
	@Path("/{id}/like")
	public Response likePage(@PathParam("id") final long id) {
		pageService.likePage(getEmailFromToken(), id);
		return Response.noContent().build();
	}
	
	@PUT
	@Path("/{id}/unlike")
	public Response unlikePage(@PathParam("id")final long id) {
		pageService.unlikePage(getEmailFromToken(), id);
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deletePage(@PathParam("id") final long id) {
		pageService.deletePage(id);
		return Response.noContent().build();
	}
}
