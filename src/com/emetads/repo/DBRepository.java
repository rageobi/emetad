package com.emetads.repo;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import com.emetads.controller.User;
import com.emetads.util.DBUtil;

public class DBRepository {
private Connection dbConnection;
	
	public DBRepository() {
		dbConnection = DBUtil.getConnection();
	}
	
	public boolean checkUserPresence(String email) {
		ResultSet result=null;
		PreparedStatement prepStatement=null;
		try {
			prepStatement = dbConnection.prepareStatement("Select count(*) from usertable where email= ?");
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
		PreparedStatement prepStatement=null;
		try {
			prepStatement = dbConnection.prepareStatement("Select * from usertable where email= ?");
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
	public List<User> getUsersInList(String gender, String intIn, int userID) {
		ResultSet result=null;
		ArrayList<User> users = new ArrayList<User>();
		PreparedStatement prepStatement=null;
		try {
			prepStatement = dbConnection.prepareStatement("Select * from usertable where gender= ? and interested_in =? and user_id<> ?");
			prepStatement.setString(1,intIn);
			prepStatement.setString(2,gender);
			prepStatement.setInt(3,userID);
			result = prepStatement.executeQuery();
			
			if (result != null) {	
				while (result.next()) {
					User user = new User();
					user.setCity(result.getString(6));
					user.setCreatedDate(result.getDate(3));
					user.setdisplayPicture(result.getBlob(13).getBinaryStream());
					user.setDob(result.getDate(9));
					user.setFirstname(result.getString(5));
					user.setGender(result.getString(10));
					user.setIntrestedIn(result.getString(11));
					user.setLastname(result.getString(4));
					user.setPhone(result.getString(8));
					user.setState(result.getString(7));
					user.setUserID(result.getInt(1));
					user.setEmail( result.getString(2));
					user.setDescription(result.getString(14));
					users.add(user);
				}
			}
			else{
				users =null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
	        if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
	        if (prepStatement != null) try { prepStatement.close(); } catch (SQLException logOrIgnore) {}
	    }
		return users;
	}
	
	public List<User> getUsersInListAfterSearch(String gender, String intIn, int userID, String search) {
		ResultSet result=null;
		ArrayList<User> users = new ArrayList<User>();
		PreparedStatement prepStatement=null;
		try {
			prepStatement = dbConnection.prepareStatement("Select * from usertable where gender=? and interested_in =? and user_id<>? and ((instr(lower(description),?,1,1)>0) or (instr(lower(last_name),?,1,1)>0) or (instr(lower(first_name),?,1,1)>0) or (instr(lower(state),?,1,1)>0) or (instr(lower(city),?,1,1)>0))");
			prepStatement.setString(1,intIn);
			prepStatement.setString(2,gender);
			prepStatement.setInt(3,userID);
			prepStatement.setString(4,search);
			prepStatement.setString(5,search);
			prepStatement.setString(6,search);
			prepStatement.setString(7,search);
			prepStatement.setString(8,search);
			
			result = prepStatement.executeQuery();
			
			if (result != null) {	
				while (result.next()) {
					User user = new User();
					user.setCity(result.getString(6));
					user.setCreatedDate(result.getDate(3));
					user.setdisplayPicture(result.getBlob(13).getBinaryStream());
					user.setDob(result.getDate(9));
					user.setFirstname(result.getString(5));
					user.setGender(result.getString(10));
					user.setIntrestedIn(result.getString(11));
					user.setLastname(result.getString(4));
					user.setPhone(result.getString(8));
					user.setState(result.getString(7));
					user.setUserID(result.getInt(1));
					user.setEmail( result.getString(2));
					user.setDescription(result.getString(14));
					users.add(user);
				}
			}
			else{
				users =null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
	        if (result != null) try { result.close(); } catch (SQLException logOrIgnore) {}
	        if (prepStatement != null) try { prepStatement.close(); } catch (SQLException logOrIgnore) {}
	    }
		return users;
	}
	
	public int signupUser(String fName,String lName, String Email, String gender, String password, String state, String city, String mobileNumber, Date dob, String iGender, InputStream inputsream, String description) {
		try {
			PreparedStatement prepStatement = dbConnection.prepareStatement("INSERT INTO usertable (EMAIL,create_date,last_name,first_name,city,state,phone,dob,gender,interested_in,password,dp_image,description) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
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
			 prepStatement.setString(13, description);
			return prepStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return 0;
	}
	public int updateUser(String fName,String lName, String Email, String gender, String state, String city, String mobileNumber, Date dob, String iGender, InputStream inputsream, String description, String userID) {
		try {
			PreparedStatement prepStatement = dbConnection.prepareStatement("update usertable set EMAIL=?,create_date=?,last_name=?,first_name=?,city=?,state=?,phone=?,dob=?,gender=?,interested_in=?,dp_image=?,description=? where user_id=?");
			
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
			 if (inputsream != null) {
	                // fetches input stream of the upload file for the blob column
				 prepStatement.setBinaryStream(11, inputsream);
	            }
			//prepStatement.setInt(12, 1);
			 prepStatement.setString(12, description);
			 prepStatement.setString(13, userID);
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
	
	public int storeLikes(String liker, String likee) {
		try {
			PreparedStatement prepStatement = dbConnection.prepareStatement("Insert into emetaduserlikes(liker_user_id,likee_user_id) values (?,?)");
			prepStatement.setString(1, liker);
			prepStatement.setString(2, likee);
			return prepStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return 0;
	}
	
	public boolean checkLikeExists(String liker, String likee) {
		try {
			PreparedStatement prepStatement = dbConnection.prepareStatement("select count(*) from emetaduserlikes where liker_user_id=? and likee_user_id=?");
			prepStatement.setString(1, liker);
			prepStatement.setString(2, likee);
			ResultSet result = prepStatement.executeQuery();
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
	public ResultSet getUserNameAndEmail(String userID) {
		ResultSet email=null;
		try {	
			PreparedStatement prepStatement = dbConnection.prepareStatement("select email ,first_name,last_name from usertable where user_id = ?");
			prepStatement.setString(1, userID);			
			ResultSet result = prepStatement.executeQuery();
			if (result != null) {
				while (result.next()) {
					return result;
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}
}
