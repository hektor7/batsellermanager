package org.hektor7.batsellermanager.service.impl;

import java.util.List;

import org.hektor7.batsellermanager.domain.AppUser;
import org.hektor7.batsellermanager.domain.repository.AppUserRepository;
import org.hektor7.batsellermanager.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation class of AppUser's service.
 * 
 * @author hector
 *
 */
@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AppUserRepository appUserRepository;

	public List<AppUser> getAllAppUsers() {
		return this.appUserRepository.findAll();
	}

	public AppUser getAppUserById(long appUserId) {
		return this.appUserRepository.findOne(appUserId);
	}

	public AppUser addAppUser(AppUser appUser) {
		return this.appUserRepository.save(appUser);
	}

	public void removeAppUser(AppUser appUser) {
		this.appUserRepository.delete(appUser);
		
	}

}
