package a;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import twitter4j.conf.ConfigurationBuilder;


public class TwitterStreamClass {
	
	public static void main(String[] args) throws NumberFormatException, TwitterException   {
    	
	int ch;
	Scanner sc=new Scanner(new InputStreamReader(System.in));	
	Operations op=new Operations();
	 ArrayList<Tweet> tt=op.Display();


        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey("Oa6CxrOdy61gRhBcFLFtiDOtN");
        cb.setOAuthConsumerSecret("UNACeL7npWUgSxa37q6tNgUGw5W9b3AMUT1eN31IKV3XueLzC4");
        cb.setOAuthAccessToken("82365475-2jwmlThn8sq5yCQyjIqYJ55cpl5liMiBEzZVDlmZN");
        cb.setOAuthAccessTokenSecret("lPvZEwhqA7TPoEbDgMAqaNHqBX0JD3J9Mb4TdGs9992tS");

//             TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

             Twitter  twitter = new TwitterFactory(cb.build()).getInstance();
            
             System.out.println("\nMENU\n1.Insert into Mysql databse\n2.Search by Username\n3.Display all tweets stored\n4.Sentiment Analysis\nEnter your choice:\n");
             ch=sc.nextInt();
             
     switch(ch)
     {
     
     case 1:
             String username;
           	 String profileLocation;
           	 String content;
           	 Date dd;		 //java.util.Date
           	
        Query query = new Query("happy");
      //  Date date = new Date();
        //String modifiedDate= new SimpleDateFormat("yyyymmdd").format(date);
        query.setSince("2017-10-07");
        query.setUntil("2017-10-08");

        QueryResult result;
          
        do {
           
            result = twitter.search(query);
            List<Status> tweets = result.getTweets(); 
            for (Status tweet : tweets) {
            	username=tweet.getUser().getScreenName();
            	profileLocation=tweet.getUser().getLocation();
            	content=tweet.getText();
            	dd=tweet.getCreatedAt();
            	System.out.println("\n"+username);
            	System.out.println("\n"+profileLocation);
            	System.out.println("\n"+dd);
            	System.out.println("\n"+content);
            	 
            	
               	java.sql.Date sqlDate = new java.sql.Date(dd.getTime()); //Converting java.util.Date to java.sql.Date

            	System.out.println("\n--------------------------------------------------------------------");

            	Tweet tp=new Tweet(username, sqlDate, content);
         		                 
         		op.Insert(tp);
                }
              } while ((query = result.nextQuery()) != null); 
      break;
        
     case 2:
    	 
    	 System.out.println("\nEnter username to search: ");
    	 String s=sc.next();
     	 
    	 Tweet tp=new Tweet("",null,"");
  		 
  		 tp=op.Search(s);
  		 
    	 tp.display();
    	 break;
    	 
     case 3:
  		     	     	 
    	 for (int i = 0; i < tt.size(); i++) 
    	 {
             Tweet tweet = tt.get(i);
             tweet.display();
         }    	 
    	 break;
    	 
     case 4: 
    	 
    	 NLP.init();
 		for(Tweet t1 : tt) 
    	 {
    		NLP.computeSentiment(t1.tweet);
 	
    	 }
    	 
    	 break;
    	 
    default: System.out.println("Wrong choice.");
    	 
     }
     

}}
