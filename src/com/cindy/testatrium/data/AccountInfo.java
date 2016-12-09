package com.cindy.testatrium.data;

/**
 * Encapsulates the user Account Info
 * 
 * @author Cindy
 */
public class AccountInfo {

	public enum Status {BLOCKED, ACTIVE};
	public enum Role {AUTHENTICATED_USER, ADMINISTRATOR, EDITOR};
	
	private String username;
	private String displayName;
	private String email;
	private Status status;
	private Role role;
	
}
