package org.hektor7.batsellermanager.service;

import java.util.List;

import org.hektor7.batsellermanager.domain.AppUser;

/**
 * AppUser Service
 * 
 * @author hector
 *
 */
public interface AppUserService {

	/**
	 * Method that obtains all appUsers.
	 * 
	 * @return list of appUsers
	 */
	public List<AppUser> getAllAppUsers();

	/**
	 * It obtains an AppUser through its id
	 * 
	 * @param appUserId
	 *            id
	 * @return an AppUser
	 */
	public AppUser getAppUserById(long appUserId);

	/**
	 * Method to save an appUser.
	 * 
	 * @param appUser
	 *            appUser to be saved
	 * @return an AppUser
	 */
	public AppUser saveAppUser(AppUser appUser);

	/**
	 * Method to remove an appUser
	 * 
	 * @param appUser
	 *            appUser to be removed
	 */
	public void removeAppUser(AppUser appUser);

	/**
	 * Method to get an AppUser through given user name.
	 * 
	 * @param userName user name for filter
	 * @return AppUser with the given user name
	 */
	public AppUser getAppUserByUserName(String userName);

}
