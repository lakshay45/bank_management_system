package BankingSystem;

import java.sql.*;

public class jdbcConn
{

	Connection c;
	Statement s;
	public jdbcConn()
	{
		try{
			//Function for registering database drivers
			Class.forName("com.mysql.cj.jdbc.Driver");		

			//Establishing the connection
			c=DriverManager.getConnection("jdbc:mysql:///bank_management","root","lakshay452000");			//last 2 are username and password used for mysql

			//this will be used to perform queries on database
			s=c.createStatement();

		}catch(Exception e){
			System.out.println("Error connecting with database");
			System.out.println("Error "+e);
		}	
	}
}