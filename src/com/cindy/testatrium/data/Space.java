package com.cindy.testatrium.data;

public class Space {

	public enum GroupVisibility {PUBLIC, PRIVATE};
	
	private String title;
	private String summary;
	private String description;
	private GroupVisibility groupVisibilty;
	private boolean provideMenuLink;
	
	
	public Space() {
		super();
		title = "";
		summary = "";
		description = "";
		groupVisibilty = GroupVisibility.PUBLIC;
		provideMenuLink = false;
		
	}

	public Space(String title, String summary, String description, GroupVisibility groupVisibilty,
			boolean provideMenuLink) {
		super();
		this.title = title;
		this.summary = summary;
		this.description = description;
		this.groupVisibilty = groupVisibilty;
		this.provideMenuLink = provideMenuLink;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public GroupVisibility getGroupVisibilty() {
		return groupVisibilty;
	}
	public void setGroupVisibilty(GroupVisibility groupVisibilty) {
		this.groupVisibilty = groupVisibilty;
	}
	public boolean isProvideMenuLink() {
		return provideMenuLink;
	}
	public void setProvideMenuLink(boolean provideMenuLink) {
		this.provideMenuLink = provideMenuLink;
	}
	
	
}
