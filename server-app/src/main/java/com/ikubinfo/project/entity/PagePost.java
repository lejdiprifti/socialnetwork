package com.ikubinfo.project.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="post",schema="socialnetwork")
public class PagePost extends Post {
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="page_id")
	private Page page;
	
	public PagePost() {
		
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
