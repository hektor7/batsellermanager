package org.hektor7.batsellermanager.validator;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hektor7.batsellermanager.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Adapter for use of both types of validations Spring Validations and
 * Bean Validations at same time.
 * 
 * @author hector
 *
 */
public class CustomerValidator implements Validator {

	@Autowired
	private javax.validation.Validator beanValidator;

	private Set<Validator> springValidators;

	public CustomerValidator() {
		springValidators = new HashSet<Validator>();
	}

	public void setSpringValidators(Set<Validator> springValidators) {
		this.springValidators = springValidators;
	}

	public boolean supports(Class<?> clazz) {
		return Customer.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		// It returns all of the constraints violation (bean validations)
		Set<ConstraintViolation<Object>> constraintViolations = beanValidator
				.validate(target); 

		// Handling of each violation for reject the message later. 
		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			String propertyPath = constraintViolation.getPropertyPath()
					.toString();
			String message = constraintViolation.getMessage();
			errors.rejectValue(propertyPath, "", message);
		}

		// Handling of Spring validations. The springValidators collection is defined in the applicationContext file.
		for (Validator validator : springValidators) {
			validator.validate(target, errors);
		}
	}
}