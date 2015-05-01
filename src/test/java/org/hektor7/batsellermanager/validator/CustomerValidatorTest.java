package org.hektor7.batsellermanager.validator;

import org.apache.commons.lang3.RandomStringUtils;
import org.hektor7.batsellermanager.domain.AddressInfo;
import org.hektor7.batsellermanager.domain.ContactInfo;
import org.hektor7.batsellermanager.domain.Customer;
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
public class CustomerValidatorTest {

	@Autowired
	private CustomerValidator customerValidator;

	@Test
	public void customer_with_null_name() {
		// Arrange
		Customer customer = this.createValidCustomer();
		customer.setName(null);
		
		BindException bindException = new BindException(customer, "customer");

		// Act
		ValidationUtils.invokeValidator(this.customerValidator, customer,
				bindException);

		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		Assert.assertTrue(bindException.getMessage().contains("no puede estar vacío"));
	}
	
	//TODO: Customer with empty first surname
	//TODO: Customer with empty costumer's code
	//TODO: Customer with invalid AddressInfo
	//TODO: Costomer with invalid ContactInfo
	
	private Customer createValidCustomer() {
		Customer customer = new Customer();
		
		customer.setCustomerCode(RandomStringUtils.randomAlphanumeric(5));
		customer.setName(RandomStringUtils.randomAlphabetic(5));
		customer.setFirstSurname(RandomStringUtils.randomAlphabetic(10));
		customer.setSecondSurname(RandomStringUtils.randomAlphabetic(10));
		
		customer.getAddressInfo().add(this.createValidAddressInfo());
		customer.getContactInfo().add(this.createValidContactInfo());
		
		
		return customer;
	}

	private ContactInfo createValidContactInfo() {
		ContactInfo contactInfo = new ContactInfo();
		
		contactInfo.setInfoName(RandomStringUtils.randomAlphabetic(5));
		contactInfo.setInfoType(ContactInfoTypes.EMAIL);
		contactInfo.setInfoValue(RandomStringUtils.randomAlphabetic(20));
		
		return contactInfo;
	}

	private AddressInfo createValidAddressInfo() {
		
		AddressInfo addressInfo = new AddressInfo();
		
		addressInfo.setAddressInfo(RandomStringUtils.randomAlphabetic(50));
		addressInfo.setAddressName(RandomStringUtils.randomAlphabetic(5));
		addressInfo.setCountry(RandomStringUtils.randomAlphabetic(5));
		addressInfo.setExtraInfo(RandomStringUtils.randomAlphabetic(50));
		addressInfo.setMainAddress(true);
		addressInfo.setState(RandomStringUtils.randomAlphabetic(5));
		addressInfo.setZipcode(RandomStringUtils.randomAlphabetic(5));
		
		return addressInfo;
		
	}	

}