package org.hektor7.batsellermanager.validator;

import org.apache.commons.lang3.RandomStringUtils;
import org.hektor7.batsellermanager.domain.AddressInfo;
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
public class AddressInfoValidatorTest {

	@Autowired
	private AddressInfoValidator addressInfoValidator;

	@Test
	public void addressInfo_null_addressName() {
		// Arrange
		AddressInfo addressInfo = this.createValidAddressInfo();
		addressInfo.setAddressName(null);
		
		BindException bindException = new BindException(addressInfo, "addressInfo");

		// Act
		ValidationUtils.invokeValidator(this.addressInfoValidator, addressInfo,
				bindException);

		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		Assert.assertTrue(bindException.getMessage().contains("no puede estar vacío"));
	}
	
	
	@Test
	public void addressInfo_null_addressInfo() {
		// Arrange
		AddressInfo addressInfo = this.createValidAddressInfo();
		addressInfo.setAddressInfo(null);
		
		BindException bindException = new BindException(addressInfo, "addressInfo");

		// Act
		ValidationUtils.invokeValidator(this.addressInfoValidator, addressInfo,
				bindException);

		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		Assert.assertTrue(bindException.getMessage().contains("no puede estar vacío"));
	}
	
 
	@Test
	public void addressInfo_null_state() {
		// Arrange
		AddressInfo addressInfo = this.createValidAddressInfo();
		addressInfo.setState(null);
		
		BindException bindException = new BindException(addressInfo, "addressInfo");

		// Act
		ValidationUtils.invokeValidator(this.addressInfoValidator, addressInfo,
				bindException);

		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		Assert.assertTrue(bindException.getMessage().contains("no puede estar vacío"));
	}
	

	@Test
	public void addressInfo_nonnumeric_zipcode() {
		// Arrange
		AddressInfo addressInfo = this.createValidAddressInfo();
		addressInfo.setZipcode("AABBV");
		
		BindException bindException = new BindException(addressInfo, "addressInfo");

		// Act
		ValidationUtils.invokeValidator(this.addressInfoValidator, addressInfo,
				bindException);

		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		Assert.assertTrue(bindException.getMessage().contains("tiene que corresponder a la expresión regular"));
	}
	

	@Test
	public void addressInfo_small_zipcode() {
		// Arrange
		AddressInfo addressInfo = this.createValidAddressInfo();
		addressInfo.setZipcode("23");
		
		BindException bindException = new BindException(addressInfo, "addressInfo");

		// Act
		ValidationUtils.invokeValidator(this.addressInfoValidator, addressInfo,
				bindException);

		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		Assert.assertTrue(bindException.getMessage().contains("el tamaño"));
	}

	private AddressInfo createValidAddressInfo() {
		
		AddressInfo addressInfo = new AddressInfo();
		
		addressInfo.setAddressInfo(RandomStringUtils.randomAlphabetic(50));
		addressInfo.setAddressName(RandomStringUtils.randomAlphabetic(5));
		addressInfo.setCountry(RandomStringUtils.randomAlphabetic(5));
		addressInfo.setExtraInfo(RandomStringUtils.randomAlphabetic(50));
		addressInfo.setMainAddress(true);
		addressInfo.setState(RandomStringUtils.randomAlphabetic(5));
		addressInfo.setZipcode(RandomStringUtils.randomNumeric(5));
		
		return addressInfo;
		
	}	

}