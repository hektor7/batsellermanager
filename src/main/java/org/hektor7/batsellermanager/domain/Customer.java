package org.hektor7.batsellermanager.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Customer
 *
 */
@Entity
public class Customer implements Serializable {

	
	private static final long serialVersionUID = -3045826925936099975L;
	
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String name;
	@NotNull
	private String firstSurname;
	
	private String secondSurname;
	
	@Column(unique = true)
	private String customerCode;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<ContactInfo> contactInfo;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<AddressInfo> addressInfo;
	

	public Customer() {
		super();
		this.contactInfo = new ArrayList<ContactInfo>();
		this.addressInfo = new ArrayList<AddressInfo>();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getFirstSurname() {
		return this.firstSurname;
	}

	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}   
	public String getSecondSurname() {
		return this.secondSurname;
	}

	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}   
	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
	public List<AddressInfo> getAddressInfo() {
		return addressInfo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", firstSurname="
				+ firstSurname + ", secondSurname=" + secondSurname
				+ ", customerCode=" + customerCode + "]";
	}
   
}
