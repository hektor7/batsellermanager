package org.hektor7.batsellermanager.domain.repository;

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
public class AppUserRepositoryTest {
	
	@Autowired
	AppUserRepository appUserRepository;
	
	@Test
	public void insert_valid_user(){
		AppUser newUser = this.appUserRepository.save(this.createUserForInser());
		
		Assert.assertTrue(newUser!=null);
	}

	private AppUser createUserForInser() {
		AppUser newUser = new AppUser();
		
		newUser.setName(RandomStringUtils.random(10));
		newUser.setFirstSurname(RandomStringUtils.random(10));
		newUser.setSecondSurname(RandomStringUtils.random(10));
		newUser.setUserName(RandomStringUtils.random(5));
		
		return newUser;
	}

}
