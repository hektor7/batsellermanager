package org.hektor7.batsellermanager.validator;

import org.apache.commons.lang3.RandomStringUtils;
import org.hektor7.batsellermanager.domain.ContactInfo;
import org.hektor7.batsellermanager.domain.enums.ContactInfoTypes;
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
public class ContactInfoValidatorTest {

	@Autowired
	private ContactInfoValidator contactInfoValidator;
	
	@Test
	public void contactInfo_null_infoType() {
		// Arrange
		ContactInfo contactInfo = this.createValidContactInfo();
		contactInfo.setInfoType(null);
		
		BindException bindException = new BindException(contactInfo, "contactInfo");

		// Act
		ValidationUtils.invokeValidator(this.contactInfoValidator, contactInfo,
				bindException);

		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		Assert.assertTrue(bindException.getMessage().contains("no puede ser null"));
	}
	
	@Test
	public void contactInfo_null_infoName() {
		// Arrange
		ContactInfo contactInfo = this.createValidContactInfo();
		contactInfo.setInfoName(null);
		
		BindException bindException = new BindException(contactInfo, "contactInfo");

		// Act
		ValidationUtils.invokeValidator(this.contactInfoValidator, contactInfo,
				bindException);

		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		Assert.assertTrue(bindException.getMessage().contains("no puede estar vacío"));
	}
	
	@Test
	public void contactInfo_null_infoValue() {
		// Arrange
		ContactInfo contactInfo = this.createValidContactInfo();
		contactInfo.setInfoValue(null);
		
		BindException bindException = new BindException(contactInfo, "contactInfo");

		// Act
		ValidationUtils.invokeValidator(this.contactInfoValidator, contactInfo,
				bindException);

		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		Assert.assertTrue(bindException.getMessage().contains("no puede estar vacío"));
	}

	private ContactInfo createValidContactInfo() {
		ContactInfo contactInfo = new ContactInfo();
		
		contactInfo.setInfoName(RandomStringUtils.randomAlphabetic(5));
		contactInfo.setInfoType(ContactInfoTypes.EMAIL);
		contactInfo.setInfoValue(RandomStringUtils.randomAlphabetic(20));
		
		return contactInfo;
	}	

}