package com.apigateway.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class DoctorDto {
	
	@NotEmpty
	@Size(min = 2,message="firstName should have  at least have 2 characters")
	@Pattern(regexp = "^[a-zA-Z]+$" , message="Enter alphbets or characters only")
	private String firstName;
	
	@NotEmpty
	@Size(min = 1,message="lastName should have  at least have 1 characters")
	@Pattern(regexp = "^[a-zA-Z]+$" , message="Enter alphbets or characters only")
	private String lastName;
	
	@NotEmpty
	@Email(message="Enter valid email Id")
	private String email;
	
	@NotEmpty
	@NotNull(message="Status date is a required field")    
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}", message=" Invalid date format and date shloud be (yyyy-mm-dd)")
	private String dob;
	
	@NotEmpty
	@Size(min = 5,message ="username should have atleast 5 characters")
	private String userName;
	
	@NotEmpty
	@Size(min = 8,message="password should have atleast 8 characters")
	private String password;
	
	
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


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}




	@Override
	public String toString() {
		return "DoctorRequest [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", dob=" + dob
				+ ", userName=" + userName + ", password=" + password + "]";
	}

	
	
}
