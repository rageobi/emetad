package com.emetads.repo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.emetads.util.DBUtil;

public class DBRepository {
private Connection dbConnection;
	
	public DBRepository() {
		dbConnection = DBUtil.getConnection();
	}
	
	public ResultSet findByUserName(String userID) {
		//dbConnection = DBUtil.getConnection();
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

}
