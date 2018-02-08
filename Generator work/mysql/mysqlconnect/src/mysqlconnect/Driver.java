package mysqlconnect;

import java.sql.*;

public class Driver {

	public static void main(String[] args) {
		try{
			//1. Get connection
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lightninglog", "ehrem", "flashy!");
			//2. Create Statement
			Statement myState = myConn.createStatement();
			//3. Execute SQL query
			ResultSet myRs = myState.executeQuery("select * from data");
			//4. Process result set
			while(myRs.next()){
				System.out.println(myRs.getString("PlayerName"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
