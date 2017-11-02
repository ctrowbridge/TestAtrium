package com.cindy.testatrium.data;

/**
 * Encapsulates the fields in the User Settings page.
 *  
 * @author Cindy
 */
public class UserSettings {

	public enum EmailPref {PLAIN_TEXT, HTML};
	
	/**
	 *  Digest Grouping:
	 *   ONE_DIGEST - Send one digest email per group
     *   COMBINED_DIGEST - Send one combined digest email for all groups
	 */
	public enum DigestGroupingPref {ONE_DIGEST, COMBINED_DIGEST};
	
	private EmailPref emailPref;
	private DigestGroupingPref digestPref;
	
	public UserSettings() {
		super();
		this.emailPref = EmailPref.HTML;
		this.digestPref = DigestGroupingPref.ONE_DIGEST;
	}
	
	public UserSettings(EmailPref emailPref, DigestGroupingPref digestPref) {
		super();
		this.emailPref = emailPref;
		this.digestPref = digestPref;
	}

	public EmailPref getEmailPref() {
		return emailPref;
	}

	public void setEmailPref(EmailPref emailPref) {
		this.emailPref = emailPref;
	}

	public DigestGroupingPref getDigestPref() {
		return digestPref;
	}

	public void setDigestPref(DigestGroupingPref digestPref) {
		this.digestPref = digestPref;
	}

	@Override
	public String toString() {
		return "UserSettings [emailPref=" + emailPref + ", digestPref=" + digestPref + "]";
	}

	public String emailPrefToString() {
		
		switch(emailPref) {
		case PLAIN_TEXT:
			return "Plain text";
		case HTML:
			return "HTML";
		}
		return "";
	}
	
	public String digestPrefToString() {
		
		switch(digestPref) {
		case COMBINED_DIGEST:
			return "Combined digest";
		case ONE_DIGEST:
			return "One digest";
		}
		return "";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((digestPref == null) ? 0 : digestPref.hashCode());
		result = prime * result + ((emailPref == null) ? 0 : emailPref.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSettings other = (UserSettings) obj;
		if (digestPref != other.digestPref)
			return false;
		if (emailPref != other.emailPref)
			return false;
		return true;
	}
	
}
