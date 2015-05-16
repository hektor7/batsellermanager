package org.hektor7.batsellermanager.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.hektor7.batsellermanager.domain.AppUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-DispatcherServlet-context.xml")
public class AppUserServiceTest {

	@Autowired
	private AppUserService appUserService;

	
	@Test
	public void testGetAppUsersById() throws Exception {
		// Arrange
		AppUser appUser = this.obtainInsertedAppUser(); 

		Assert.assertTrue(appUser.equals(this.appUserService.getAppUserById(appUser.getId())));
	}
	
	@Test
	public void testAddAndRemoveAppUser() throws Exception {
		// Arrange
		AppUser appUser = this.createValidAppUser();
		
		AppUser persistedAppUser = this.appUserService.saveAppUser(appUser);
		
		Assert.assertTrue(persistedAppUser.equals(this.appUserService.getAppUserById(persistedAppUser.getId())));
		
		this.appUserService.removeAppUser(persistedAppUser);

	}
	
	@Test
	public void testModifyAppUser() {
		//Arrange
		AppUser appUser = this.obtainInsertedAppUser();
		
		String oldName = appUser.getName();
		
		appUser.setName("MODIFIED");
		
		appUser = this.appUserService.saveAppUser(appUser);
		
		AppUser appUserModified = this.appUserService.getAppUserByUserName(appUser.getUserName());
		
		Assert.assertTrue(!appUserModified.getName().equals(oldName));
	}

	
	private AppUser obtainInsertedAppUser() {
		AppUser appUser = null;
		
		if (!this.appUserService.getAllAppUsers().isEmpty()) {
			appUser = this.appUserService.getAllAppUsers().get(0);
		}else {
			appUser = this.insertValidUser();
		}
		
		return appUser;
	}

	private AppUser insertValidUser() {
		return this.appUserService.saveAppUser(this.createValidAppUser());
		
	}

	private AppUser createValidAppUser() {
		
		AppUser appUser = new AppUser();
		
		appUser.setFirstSurname(RandomStringUtils.randomAlphabetic(10));
		appUser.setSecondSurname(RandomStringUtils.randomAlphabetic(10));
		appUser.setName(RandomStringUtils.randomAlphabetic(5));
		appUser.setPassword(RandomStringUtils.randomAlphabetic(10));
		appUser.setUserName(RandomStringUtils.randomAlphabetic(5));
		//TODO: contactInfo
		
		return appUser;
	}

}