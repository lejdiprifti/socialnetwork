package com.ikubinfo.project.entity;

import javax.persistence.Embeddable;

@Embeddable
public class SocialLinks {

	private String facebook;
	private String instagram;
	private String linkedin;
	private String twitter;

	public SocialLinks() {

	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	@Override
	public String toString() {
		return "SocialLinks [facebook=" + facebook + ", instagram=" + instagram + ", linkedin=" + linkedin
				+ ", twitter=" + twitter + "]";
	}

}
