package com.ikubinfo.project.resource;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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
	
	@POST
	public Response createPage(PageModel page) throws URISyntaxException {
		return Response.created(new URI("pages/"+pageService.createPage(page, getEmailFromToken()).getId())).build();
	}
	
	@PUT
	public Response likePage(final long id) {
		pageService.likePage(getEmailFromToken(), id);
		return Response.noContent().build();
	}
	
	@PUT
	public Response unlikePage(final long id) {
		pageService.unlikePage(getEmailFromToken(), id);
		return Response.noContent().build();
	}
}
