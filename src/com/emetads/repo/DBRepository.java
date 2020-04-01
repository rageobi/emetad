package com.emetads.repo;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import com.emetads.util.DBUtil;

public class DBRepository {
private Connection dbConnection;
	
	public DBRepository() {
		dbConnection = DBUtil.getConnection();
	}
	
	public boolean checkUserPresence(String email) {
		ResultSet result=null;
		try {
			PreparedStatement prepStatement = dbConnection.prepareStatement("Select count(*) from usertable where email= ?");
			prepStatement.setString(1,email);
			result = prepStatement.executeQuery();
			if (result != null) {	
				while (result.next()) {
					if (result.getInt(1)>0) {
						return true;
					}				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public ResultSet fetchUserDetails(String email) {
		ResultSet result=null;
		try {
			PreparedStatement prepStatement = dbConnection.prepareStatement("Select * from usertable where email= ?");
			prepStatement.setString(1,email);
			result = prepStatement.executeQuery();
			if (result != null) {	
				while (result.next()) {
					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int signupUser(String fName,String lName, String Email, String gender, String password, String state, String city, String mobileNumber, Date dob, String iGender, InputStream inputsream) {
		try {
			PreparedStatement prepStatement = dbConnection.prepareStatement("INSERT INTO usertable (EMAIL,create_date,last_name,first_name,city,state,phone,dob,gender,interested_in,password,dp_image) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			
			prepStatement.setString(1, Email);
			prepStatement.setDate(2, new java.sql.Date(dob.getTime()));
			prepStatement.setString(3, lName);
			prepStatement.setString(4, fName);
			prepStatement.setString(5, city);
			prepStatement.setString(6, state);
			prepStatement.setString(7, mobileNumber);
			prepStatement.setDate(8, new java.sql.Date(dob.getTime()));
			prepStatement.setString(9, gender);
			prepStatement.setString(10, iGender);
			prepStatement.setString(11, password);
			
			 if (inputsream != null) {
	                // fetches input stream of the upload file for the blob column
				 prepStatement.setBinaryStream(12, inputsream);
	            }
			//prepStatement.setInt(12, 1);
			return prepStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return 0;
	}
	public boolean validateUserCredentials(String email, String password) {
		try {
			PreparedStatement prepStatement = dbConnection.prepareStatement("select password from usertable where email = ?");
			prepStatement.setString(1, email);			
			ResultSet result = prepStatement.executeQuery();
			if (result != null) {
				while (result.next()) {
					if (result.getString(1).equals(password)) {
						return true;
					}
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
