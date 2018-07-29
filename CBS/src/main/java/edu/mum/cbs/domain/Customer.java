package edu.mum.cbs.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.authentication.UserCredentials;


@Entity
public class Customer implements Serializable{

	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Value("#{new Integer.parseInteger('${id}')}")
	private long id;
	
	@NotEmpty (message = "{NotEmpty}")
	@Column(name = "first_name")
	private String firstName;
	
	@NotEmpty (message = "{NotEmpty}")
	@Column(name = "last_name")
	private String lastName;
	
	@NotEmpty (message = "{NotEmpty}")
	@Column(name = "ssn")
	private String SSN;
	
	@Email
	@NotEmpty (message = "{NotEmpty}")
	@Column(name="email")
	private String Email;
	
/*	@Column(name = "status")
	private String status;*/
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	private List<Account> accounts;

	
	/*@OneToOne(fetch=FetchType.EAGER,  cascade = CascadeType.ALL) 
 	@JoinColumn(name="customerId") 
 	UserCredentials userCredentials;*/
 	
	public long getId() {
		return id;

	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer() {
		accounts = new ArrayList<Account>();
	}

	@Basic
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	
	/*public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}*/

	
	public List<Account> getAccounts() {
		return accounts;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	

}
