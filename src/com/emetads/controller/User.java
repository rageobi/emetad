package com.emetads.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

public class User {
	private int userID;
	private Date createdDate;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String city;
	private String state;
	private String phone;
	private Date dob;
	private String gender;
	private String intrestedIn;
	private InputStream displayPicture;
	private String description;
	public User() {
		
	}
	public User(ResultSet result) throws SQLException {
		super();
		this.userID = result.getInt(1);
		this.email = result.getString(2);
		this.createdDate = result.getDate(3);
		this.lastname = result.getString(4);
		this.firstname = result.getString(5);
		this.city = result.getString(6);
		this.state = result.getString(7);
		this.phone = result.getString(8);
		this.dob = result.getDate(9);
		this.gender = result.getString(10);
		this.intrestedIn = result.getString(11);
		this.password = result.getString(12);
		this.displayPicture = result.getBlob(13).getBinaryStream();
		this.description = result.getString(14);
	}
	
	public String getBase64Image(InputStream displayPicture) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;

		while ((bytesRead = displayPicture.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}

		byte[] imageBytes = outputStream.toByteArray();
		return Base64.getEncoder().encodeToString(imageBytes);
	}

	public InputStream getdisplayPicture() {
		return displayPicture;
	}

	public void setdisplayPicture(InputStream displayPicture) {
		this.displayPicture = displayPicture;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIntrestedIn() {
		return intrestedIn;
	}

	public void setIntrestedIn(String intrestedIn) {
		this.intrestedIn = intrestedIn;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
