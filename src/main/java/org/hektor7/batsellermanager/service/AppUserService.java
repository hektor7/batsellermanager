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
	 * Method to add an appUser.
	 * 
	 * @param appUser
	 *            new appUser
	 * @return an AppUser
	 */
	public AppUser addAppUser(AppUser appUser);

	/**
	 * Method to remove an appUser
	 * 
	 * @param appUser
	 *            appUser to be removed
	 */
	public void removeAppUser(AppUser appUser);

}
