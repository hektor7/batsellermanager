package org.hektor7.batsellermanager.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: AddressInfo
 *
 */
@Entity
public class AddressInfo implements Serializable {

	private static final long serialVersionUID = -6768213390999898536L;

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(nullable = false)
	private String addressName;

	@NotEmpty
	@Column(nullable = false)
	private String addressInfo;

	// TODO: Create an annotation @State
	@NotEmpty
	@Column(nullable = false)
	private String state;

	@Pattern(regexp = "[0-9]+")
	@Size(max = 5, min=5)
	private String zipcode;

	// TODO: Create an annotation @Country
	@NotEmpty
	@Column(nullable = false)
	private String country;

	private String extraInfo;

	private boolean mainAddress;

	public AddressInfo() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressInfo() {
		return this.addressInfo;
	}

	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public boolean getMainAddress() {
		return this.mainAddress;
	}

	public void setMainAddress(boolean mainAddress) {
		this.mainAddress = mainAddress;
	}

	public String getAddressName() {
		return this.addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

}
