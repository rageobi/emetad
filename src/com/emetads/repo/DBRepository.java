package com.emetads.repo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import com.emetads.util.DBUtil;

public class DBRepository {
private Connection dbConnection;
	
	public DBRepository() {
		dbConnection = DBUtil.getConnection();
	}
	
	public ResultSet findByUserName(String userID) {
		ResultSet result=null;
		try {
			PreparedStatement prepStatement = dbConnection.prepareStatement("Select * from usertable where USER_ID= ?");
			prepStatement.setInt(1,Integer.parseInt(userID));
			result = prepStatement.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int signupUser(String fName,String lName, String Email, String gender, String password, String state, String city, String mobileNumber, Date dob, String iGender) {
		try {
			PreparedStatement prepStatement = dbConnection.prepareStatement("INSERT INTO usertable (EMAIL,create_date,last_name,first_name,city,state,phone,dob,gender,interested_in,password) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			
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
			//prepStatement.setInt(12, 1);
			return prepStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return 0;
	}

}
