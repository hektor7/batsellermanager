package org.hektor7.batsellermanager.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hektor7.batsellermanager.domain.enums.ContactInfoTypes;

/**
 * Entity implementation class for Entity: ContactInfo
 *
 */
@Entity
public class ContactInfo implements Serializable {

	   
	private static final long serialVersionUID = 7645824430462575719L;

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(nullable = false)
	private ContactInfoTypes infoType;
	
	@NotNull
	@Column(nullable = false)
	private String infoName;
	
	@NotNull
	@Column(nullable = false)
	private String infoValue;
	

	public ContactInfo() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public ContactInfoTypes getInfoType() {
		return this.infoType;
	}

	public void setInfoType(ContactInfoTypes infoType) {
		this.infoType = infoType;
	}   
	public String getInfoName() {
		return this.infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}   
	public String getInfoValue() {
		return this.infoValue;
	}

	public void setInfoValue(String infoValue) {
		this.infoValue = infoValue;
	}
   
}
