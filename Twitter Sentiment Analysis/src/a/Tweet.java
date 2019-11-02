package a;

import java.sql.Date;

public class Tweet {

	public String name;
	public Date dd;
	public String tweet;

	public Tweet()
	{
	name=null;
	dd=null;
	tweet=null;
	}
	
	public Tweet(String name, Date dd, String tweet) 
	{
		this.name = name;
		this.dd = dd;
		this.tweet = tweet;
	}

	
	
	
	public void display() 
	{
		
		System.out.println("\n------------------------------------------------------------------------------------------------------------");
		System.out.println("\nName: "+this.name);
		System.out.println("\nDate: "+this.dd);
		System.out.println("\nTweet: "+this.tweet);
		
	}
	
	
}
