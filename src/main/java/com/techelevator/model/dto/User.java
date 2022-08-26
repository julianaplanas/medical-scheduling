package com.techelevator.model.dto;

import com.techelevator.model.dao.UserDAO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

	private int id;
	@NotBlank(message = "Your User Name is required")
	private String userName;
	@Size(min=8, message="Password too short, must be at least 8")
	@Pattern.List({
		@Pattern(regexp=".*[a-z].*", message="Must have a lower case"),
		@Pattern(regexp=".*[A-Z].*", message="Must have a capital")
	})
	private String password;
	@NotBlank(message = "Your Role is required")
	private String role;
	@NotBlank(message = "Your Confirmation password is required")
	private String confirmPassword;
	private boolean isPasswordConfirmed;

	@AssertTrue(message = "Both passwords must be equal")
	public boolean getPasswordConfirmed()
	{
		if(password != null) {
			return(password.equalsIgnoreCase(confirmPassword));
		}
		return true;
	}

	public String getUserName() {
		return userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isDoctor() {
		return role.equalsIgnoreCase("doctor");
	}
	public boolean isPatient() {
		return role.equalsIgnoreCase("patient");
	}

}
