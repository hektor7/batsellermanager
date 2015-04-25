package org.hektor7.batsellermanager.domain.repository;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.RandomStringUtils;
import org.hektor7.batsellermanager.domain.AppUser;
import org.hektor7.batsellermanager.domain.ContactInfo;
import org.hektor7.batsellermanager.domain.enums.ContactInfoTypes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Test for AppUser's repository
 * 
 * @author hector
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-DispatcherServlet-context.xml")
public class AppUserRepositoryTest {
	
	@Autowired
	AppUserRepository appUserRepository;
	
	@Test
	public void insert_valid_user(){
		AppUser newUser = this.appUserRepository.save(this.createUserForInsert());

		Assert.assertTrue(newUser!=null);
	}
	
	@Test
	public void insert_valid_user_and_update() {
		AppUser newUser = this.appUserRepository.save(this.createUserForInsert());
		
		newUser.setName("NAME UPDATED");
		newUser.setFirstSurname("SURNAME UPDATED");
		newUser.setSecondSurname("SURNAME UPDATED");
		newUser.setUserName("USERNAME UPDATED");
		
		AppUser updatedUser = this.appUserRepository.save(newUser);
		
		Assert.assertTrue(updatedUser.getFirstSurname().contains("UPDATED"));
		Assert.assertTrue(updatedUser.getSecondSurname().contains("UPDATED"));
		Assert.assertTrue(updatedUser.getUserName().contains("UPDATED"));
		Assert.assertTrue(updatedUser.getName().contains("UPDATED"));
		
	}
	
	@Test(expected=ConstraintViolationException.class)
	public void insert_invalid_user_null_username() {
		AppUser newUser = this.createUserForInsert();
		newUser.setUserName(null);
		
		try {
			this.appUserRepository.save(newUser);
		}catch(JpaSystemException e) {
			if (e.getCause().getCause() != null && 
					e.getCause().getCause() instanceof ConstraintViolationException) {
				throw (ConstraintViolationException) e.getCause().getCause();
			}else {
				throw e;
			}
		}
	}
	
	@Test(expected=org.hibernate.exception.ConstraintViolationException.class)
	public void insert_invalid_user_non_unique_username() {
		
		List<AppUser> existingUsers = this.appUserRepository.findAll();
		AppUser newUser = this.createUserForInsert();
		AppUser oldUser = null;
		
		
		if (!existingUsers.isEmpty()) {
			oldUser = existingUsers.get(0);
		}else {
			oldUser = this.appUserRepository.save(this.createUserForInsert());
		}
		newUser.setUserName(oldUser.getUserName());
		
		try {
			this.appUserRepository.save(newUser);
		}catch(JpaSystemException e) {
			if (e.getCause().getCause() != null && 
					e.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				throw (org.hibernate.exception.ConstraintViolationException) e.getCause().getCause();
			}else {
				throw e;
			}
		}
	}
	
	
	@Test
	public void set_password_encoded() {
		final String password="123";
		
		AppUser appUser = new AppUser();
		appUser.setPassword(password);
		
		Assert.assertTrue(!password.equals(appUser.getPassword()));
	
	}
	
	@Test
	public void insert_and_delete_appUser() {
		AppUser newUser = this.appUserRepository.save(this.createUserForInsert());
		Long idNewUser = newUser.getId();
		this.appUserRepository.delete(newUser);
		Assert.assertTrue(this.appUserRepository.findOne(idNewUser)==null);
		
	}
	
	@Test
	public void insert_and_search_inserted_appUser() {
		AppUser newUser = this.appUserRepository.save(this.createUserForInsert());
		Long idNewUser = newUser.getId();
		Assert.assertTrue(this.appUserRepository.findOne(idNewUser)!=null);
	}
	
	@Test(expected=ConstraintViolationException.class)
	public void insert_valid_user_w_invalid_ContactInfo_null_infoType() {
		
		AppUser newAppUser = this.createUserForInsert();
		
		newAppUser.getContactInfo().get(0).setInfoType(null);
		
		try {
			this.appUserRepository.save(newAppUser);
		}catch(JpaSystemException e) {
			if (e.getCause().getCause() != null && 
					e.getCause().getCause() instanceof ConstraintViolationException) {
				throw (ConstraintViolationException) e.getCause().getCause();
			}else {
				throw e;
			}
		}
	}
	
	@Test(expected=ConstraintViolationException.class)
	public void insert_valid_user_w_invalid_ContactInfo_null_infoName() {
		
		AppUser newAppUser = this.createUserForInsert();
		
		newAppUser.getContactInfo().get(0).setInfoName(null);
		
		try {
			this.appUserRepository.save(newAppUser);
		}catch(JpaSystemException e) {
			if (e.getCause().getCause() != null && 
					e.getCause().getCause() instanceof ConstraintViolationException) {
				throw (ConstraintViolationException) e.getCause().getCause();
			}else {
				throw e;
			}
		}
	}
	
	@Test(expected=ConstraintViolationException.class)
	public void insert_valid_user_w_invalid_ContactInfo_null_infoValue() {
		
		AppUser newAppUser = this.createUserForInsert();
		
		newAppUser.getContactInfo().get(0).setInfoValue(null);
		
		try {
			this.appUserRepository.save(newAppUser);
		}catch(JpaSystemException e) {
			if (e.getCause().getCause() != null && 
					e.getCause().getCause() instanceof ConstraintViolationException) {
				throw (ConstraintViolationException) e.getCause().getCause();
			}else {
				throw e;
			}
		}
	}
	

	private AppUser createUserForInsert() {
		AppUser newUser = new AppUser();
		
		newUser.setName(RandomStringUtils.randomAlphabetic(10));
		newUser.setFirstSurname(RandomStringUtils.randomAlphabetic(10));
		newUser.setSecondSurname(RandomStringUtils.randomAlphabetic(10));
		newUser.setUserName(RandomStringUtils.randomAlphabetic(5));
		
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setInfoName("DEFAULT");
		contactInfo.setInfoType(ContactInfoTypes.EMAIL);
		contactInfo.setInfoValue("asd@asf.com");
		
		newUser.getContactInfo().add(contactInfo);
		
		return newUser;
	}
	
	

}
