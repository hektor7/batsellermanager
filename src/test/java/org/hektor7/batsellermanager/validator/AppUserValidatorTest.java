package org.hektor7.batsellermanager.validator;

import org.apache.commons.lang3.RandomStringUtils;
import org.hektor7.batsellermanager.domain.AppUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

//TODO: Change assertions in order to check through i18n messages.

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-DispatcherServlet-context.xml")
public class AppUserValidatorTest {

	@Autowired
	private AppUserValidator appUserValidator;

	@Test
	public void appUser_with_null_username() {
		// Arrange
		AppUser appUser = this.createValidAppUser();
		appUser.setUserName(null);
		BindException bindException = new BindException(appUser, "appUser");

		// Act
		ValidationUtils.invokeValidator(this.appUserValidator, appUser,
				bindException);

		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		Assert.assertTrue(bindException.getMessage().contains("no puede estar vacío"));
	}
	
	@Test
	public void appUser_with_short_username() {
		// Arrange
		AppUser appUser = this.createValidAppUser();
		appUser.setUserName(RandomStringUtils.randomAlphabetic(1));
		BindException bindException = new BindException(appUser, "appUser");

		// Act
		ValidationUtils.invokeValidator(this.appUserValidator, appUser,
				bindException);

		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		Assert.assertTrue(bindException.getMessage().contains("el tamaño"));
	}
	
	@Test
	public void appUser_with_long_username() {
		// Arrange
		AppUser appUser = this.createValidAppUser();
		appUser.setUserName(RandomStringUtils.randomAlphabetic(30));
		BindException bindException = new BindException(appUser, "appUser");

		// Act
		ValidationUtils.invokeValidator(this.appUserValidator, appUser,
				bindException);

		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		Assert.assertTrue(bindException.getMessage().contains("el tamaño"));
	}

	private AppUser createValidAppUser() {
		AppUser user = new AppUser();
		user.setFirstSurname(RandomStringUtils.randomAlphabetic(10));
		user.setName(RandomStringUtils.randomAlphabetic(10));
		user.setSecondSurname(RandomStringUtils.randomAlphabetic(10));
		user.setUserName(RandomStringUtils.randomAlphabetic(10));
		return user;
	}

	

}