package org.hektor7.batsellermanager.domain.repository;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.RandomStringUtils;
import org.hektor7.batsellermanager.domain.AddressInfo;
import org.hektor7.batsellermanager.domain.ContactInfo;
import org.hektor7.batsellermanager.domain.Customer;
import org.hektor7.batsellermanager.domain.enums.ContactInfoTypes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Test for Customer's repository
 * 
 * @author hector
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-DispatcherServlet-context.xml")
public class CustomerRepositoryTest {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	public void insert_valid_customer(){
		Customer newCustomer = this.customerRepository.save(this.createCustomerForInsert());

		Assert.assertTrue(newCustomer!=null);
	}
	
	@Test
	public void insert_valid_customer_and_update() {
		Customer newCustomer = this.customerRepository.save(this.createCustomerForInsert());
		
		newCustomer.setCustomerCode("CODE UPDATED");
		newCustomer.setFirstSurname("SURNAME UPDATED");
		newCustomer.setSecondSurname("SURNAME UPDATED");
		newCustomer.setName("NAME UPDATED");
		
		Customer updatedCustomer = this.customerRepository.save(newCustomer);
		
		Assert.assertTrue(updatedCustomer.getFirstSurname().contains("UPDATED"));
		Assert.assertTrue(updatedCustomer.getSecondSurname().contains("UPDATED"));
		Assert.assertTrue(updatedCustomer.getCustomerCode().contains("UPDATED"));
		Assert.assertTrue(updatedCustomer.getName().contains("UPDATED"));
		
	}
	
	@Test(expected=ConstraintViolationException.class)
	public void insert_invalid_customer_null_name() {
		Customer newCustomer = this.createCustomerForInsert();
		newCustomer.setName(null);
		
		try {
			this.customerRepository.save(newCustomer);
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
	public void insert_invalid_customer_null_customerCode() {
		Customer newCustomer = this.createCustomerForInsert();
		newCustomer.setCustomerCode(null);
		
		try {
			this.customerRepository.save(newCustomer);
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
	public void insert_invalid_user_non_unique_customerCode() {
		
		List<Customer> existingCustomers = this.customerRepository.findAll();
		Customer newCustomer = this.createCustomerForInsert();
		Customer oldCustomer = null;
		
		
		if (!existingCustomers.isEmpty()) {
			oldCustomer = existingCustomers.get(0);
		}else {
			oldCustomer = this.customerRepository.save(this.createCustomerForInsert());
		}
		newCustomer.setCustomerCode(oldCustomer.getCustomerCode());
		
		try {
			this.customerRepository.save(newCustomer);
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
	public void insert_and_delete_customer() {
		Customer newCustomer = this.customerRepository.save(this.createCustomerForInsert());
		Long idNewCustomer = newCustomer.getId();
		this.customerRepository.delete(newCustomer);
		Assert.assertTrue(this.customerRepository.findOne(idNewCustomer)==null);
		
	}
	
	@Test
	public void insert_and_search_inserted_customer() {
		Customer newCustomer = this.customerRepository.save(this.createCustomerForInsert());
		Long idNewCustomer = newCustomer.getId();
		Assert.assertTrue(this.customerRepository.findOne(idNewCustomer)!=null);
	}
	
	@Test(expected=ConstraintViolationException.class)
	public void insert_valid_customer_w_invalid_ContactInfo_null_infoType() {
		
		Customer newCustomer = this.createCustomerForInsert();
		
		newCustomer.getContactInfo().get(0).setInfoType(null);
		
		try {
			this.customerRepository.save(newCustomer);
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
	public void insert_valid_customer_w_invalid_ContactInfo_null_infoName() {
		
		Customer newCustomer = this.createCustomerForInsert();
		
		newCustomer.getContactInfo().get(0).setInfoName(null);
		
		try {
			this.customerRepository.save(newCustomer);
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
	public void insert_valid_customer_w_invalid_ContactInfo_null_infoValue() {
		
		Customer newCustomer = this.createCustomerForInsert();
		
		newCustomer.getContactInfo().get(0).setInfoValue(null);
		
		try {
			this.customerRepository.save(newCustomer);
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
	public void insert_valid_customer_w_invalid_AddressInfo_null_addressName() {
		
		Customer newCustomer = this.createCustomerForInsert();
		
		newCustomer.getAddressInfo().get(0).setAddressName(null);
		
		try {
			this.customerRepository.save(newCustomer);
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
	public void insert_valid_customer_w_invalid_AddressInfo_null_addressInfo() {
		
		Customer newCustomer = this.createCustomerForInsert();
		
		newCustomer.getAddressInfo().get(0).setAddressInfo(null);
		
		try {
			this.customerRepository.save(newCustomer);
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
	public void insert_valid_customer_w_invalid_AddressInfo_null_state() {
		
		Customer newCustomer = this.createCustomerForInsert();
		
		newCustomer.getAddressInfo().get(0).setState(null);
		
		try {
			this.customerRepository.save(newCustomer);
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
	public void insert_valid_customer_w_invalid_AddressInfo_null_country() {
		
		Customer newCustomer = this.createCustomerForInsert();
		
		newCustomer.getAddressInfo().get(0).setCountry(null);
		
		try {
			this.customerRepository.save(newCustomer);
		}catch(JpaSystemException e) {
			if (e.getCause().getCause() != null && 
					e.getCause().getCause() instanceof ConstraintViolationException) {
				throw (ConstraintViolationException) e.getCause().getCause();
			}else {
				throw e;
			}
		}
	}

	private Customer createCustomerForInsert() {
		Customer newCustomer = new Customer();
		
		newCustomer.setName(RandomStringUtils.randomAlphabetic(10));
		newCustomer.setFirstSurname(RandomStringUtils.randomAlphabetic(10));
		newCustomer.setSecondSurname(RandomStringUtils.randomAlphabetic(10));
		newCustomer.setCustomerCode(RandomStringUtils.randomAlphabetic(5));
		
		newCustomer.getContactInfo().add(this.createContactInfo());
		
		newCustomer.getAddressInfo().add(this.createNewAddress());
		
		return newCustomer;
	}

	private ContactInfo createContactInfo() {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setInfoName("DEFAULT");
		contactInfo.setInfoType(ContactInfoTypes.EMAIL);
		contactInfo.setInfoValue("asd@asf.com");
		return contactInfo;
	}

	private AddressInfo createNewAddress() {
		AddressInfo addressInfo = new AddressInfo();
		addressInfo.setAddressInfo(RandomStringUtils.randomAlphabetic(20));
		addressInfo.setAddressName("DEFAULT");
		addressInfo.setCountry(RandomStringUtils.randomAlphabetic(10));
		addressInfo.setExtraInfo(RandomStringUtils.randomAlphabetic(40));
		addressInfo.setMainAddress(true);
		addressInfo.setState(RandomStringUtils.randomAlphabetic(20));
		addressInfo.setZipcode(RandomStringUtils.randomNumeric(5));
		return addressInfo;
	}
	
	

}
