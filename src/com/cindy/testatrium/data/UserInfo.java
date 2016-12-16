package com.cindy.testatrium.data;

/**
 * Encapsulates the User info.
 * 
 * @author Cindy
 */
public class UserInfo {
	
	public enum Roles {AUTHENTICATED_USER, ADMINSTRATOR, EDITOR};
	public enum Status {BLOCKED, ACTIVE};
	
	private String username;
	private String displayName;
	private String picture;
	private String about;
	private String email;
	private Roles role;
	private Status status;
	private String password;
	
	public UserInfo() {
		username = "";
		displayName = "";
		picture = "";
		about = "";
		email = "";
		role = Roles.EDITOR;
		status = Status.ACTIVE;
		password = "";
	}
	
	public UserInfo(String username, String password) {

		this.username = username;
		displayName = "";
		picture = "";
		about = "";
		email = "";
		role = Roles.EDITOR;
		status = Status.ACTIVE;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
