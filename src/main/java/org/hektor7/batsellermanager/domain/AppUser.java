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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

/**
 * Entity implementation class for Entity: AppUser
 *
 */
@Entity
public class AppUser implements Serializable {

	private static final long serialVersionUID = -2850113271018363328L;

	//FIXME: @Autowired annotation doesn't works
	@Transient
	private transient MessageDigestPasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("SHA-256");

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String firstSurname;

	private String secondSurname;

	@NotEmpty
	@Size(min=4, max=20)
	@Column(unique = true, nullable = false)
	private String userName;
	
	@Size(min=6, max=20)
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<ContactInfo> contactInfo;

	public AppUser() {
		super();
		this.contactInfo = new ArrayList<ContactInfo>();
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

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (!StringUtils.isEmpty(password)) {
			String encodedPassword = this.passwordEncoder.encodePassword(
					password, null);
			this.password = encodedPassword;
		}
	}

	public List<ContactInfo> getContactInfo() {
		return contactInfo;
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
		AppUser other = (AppUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", name=" + name + ", firstSurname="
				+ firstSurname + ", secondSurname=" + secondSurname
				+ ", userName=" + userName + "]";
	}

}
