package org.hektor7.batsellermanager.exception;

/**
 * Not found exception for AppUsers
 * 
 * @author hector
 *
 */
public class AppUserNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = -2648568676237701591L;
	
	private String appUserId;

	public AppUserNotFoundException(String appUserId) {
		this.appUserId = appUserId;
	}

	public String getAppUserId() {
		return appUserId;
	}

}