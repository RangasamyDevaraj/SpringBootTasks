package com.apigateway.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Doctor_Id;
	private String firstName;
	private String lastName;
	private String email;
	private String dob;
	private String userName;
	private String password;
	

	
	public Doctor(int doctor_Id, String firstName, String lastName, String email, String dob, String userName,
			String password) {
		super();
		Doctor_Id = doctor_Id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dob = dob;
		this.userName = userName;
		this.password = password;
	}

	public int getDoctor_Id() {
		return Doctor_Id;
	}
	public void setDoctor_Id(int doctor_Id) {
		Doctor_Id = doctor_Id;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getuserName() {
		return userName;
	}
	public void setuserName(String userName) {
		this.userName = userName;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	

	public Doctor() {
		super();
	}

	@Override
	public String toString() {
		return "Doctor [Doctor_Id=" + Doctor_Id + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", dob=" + dob + ", userName=" + userName + ", password=" + password + "]";
	}
	
}
