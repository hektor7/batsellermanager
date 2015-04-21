package org.hektor7.batsellermanager.domain.repository;

import org.apache.commons.lang3.RandomStringUtils;
import org.hektor7.batsellermanager.domain.AppUser;
import org.hibernate.PropertyValueException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.istack.internal.logging.Logger;


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
	
	final private Logger logger = Logger.getLogger(this.getClass());
	
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
	
	@Test(expected=PropertyValueException.class)
	public void insert_invalid_user_null_username() {
		AppUser newUser = this.createUserForInsert();
		newUser.setUserName(null);
		
		try {
			this.appUserRepository.save(newUser);
		}catch(JpaSystemException e) {
			if (e.getCause().getCause() != null && 
					e.getCause().getCause() instanceof PropertyValueException) {
				throw (PropertyValueException) e.getCause().getCause();
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

	private AppUser createUserForInsert() {
		AppUser newUser = new AppUser();
		
		newUser.setName(RandomStringUtils.randomAlphabetic(10));
		newUser.setFirstSurname(RandomStringUtils.randomAlphabetic(10));
		newUser.setSecondSurname(RandomStringUtils.randomAlphabetic(10));
		newUser.setUserName(RandomStringUtils.randomAlphabetic(5));
		
		return newUser;
	}
	
	

}
