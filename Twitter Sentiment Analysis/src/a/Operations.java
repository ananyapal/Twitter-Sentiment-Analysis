package a;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Operations {



	
//Create doesn't work
	
	public void Create()
	{
		Connection con = DB.getConnection();
		Statement stmt=null;
		
		try 
		{
		 stmt=con.createStatement();  

			stmt.executeUpdate("create database twee");  
			stmt.executeUpdate("use twee");  
			stmt.executeUpdate("create table twit(name varchar(30) not null, dd Date, tweet varchar(150) not null )");

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally {
		      if (stmt != null) {
		        try {
		          stmt.close();
		        } catch (SQLException e) {
		        } // nothing we can do
		      }
		      if (stmt != null) {
		        try {
		          stmt.close();
		        } catch (SQLException e) {
		        } 
		      }
		    }

	}
	
	
	public int Insert(Tweet tp) 
	{
		int rowsInserted = 0;
		

		Connection con = DB.getConnection();
		
				
		try 
		{
			PreparedStatement ps = con.prepareStatement("insert into twit values(?,?,?)");
			ps.setString(1, tp.name);
			
			ps.setDate(2, tp.dd);			
		
			ps.setString(3, tp.tweet);			

			rowsInserted = ps.executeUpdate();			//Query is getting fired on SQL database
			
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		return rowsInserted;
	}
	

	
	
	Tweet Search(String name)
	{
		Tweet tw = null;
		
		Connection con = DB.getConnection();
		try 
		{
			PreparedStatement ps = con.prepareStatement("Select * from twit where name = ?");
			//ps.setString(1,"JazzySpreckles" );
			ps.setString(1,name);

			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				tw = new Tweet(rs.getString("name"),rs.getDate("dd"), rs.getString("tweet"));

			}
			
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return tw;
	}
	
	
	
	ArrayList<Tweet> Display()
	{
		ArrayList<Tweet> allData = new ArrayList<Tweet>();
		
		Connection con = DB.getConnection();
		try 
		{
			PreparedStatement ps = con.prepareStatement("Select * from twit");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{

				Tweet tw = new Tweet(rs.getString("name"),rs.getDate("dd"),rs.getString("tweet"));

				allData.add(tw);
			}
			
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return allData;

	
	
	
	
	
}

}

	


