package org.hektor7.batsellermanager.domain.repository;

import org.hektor7.batsellermanager.domain.AppUser;

/**
 * Interface for AppUser's repository.
 * 
 * @author hector
 *
 */
public interface AppUserRepository extends BaseRepository<AppUser, Long>{
	
	/**
	 * Method to find an AppUser by its User Name
	 * 
	 * @param userName to be used as filter
	 * @return AppUser
	 */
	public AppUser findByUserName(String userName);
	
}
