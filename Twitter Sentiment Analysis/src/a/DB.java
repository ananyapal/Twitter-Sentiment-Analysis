package a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DB {
	
	

	static Connection con = null;
	public static Connection getConnection() 
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/twee", "root", "tiger");
			
		} 
		catch (SQLException e) 
		{
				e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
				e.printStackTrace();
		}

		return con;
	}

	public static void closeConnection()
	{
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}


}
