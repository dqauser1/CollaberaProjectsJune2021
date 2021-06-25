package com.cognixia.training.CollaberaProjectsJune2021.Team1;

	import java.io.IOException;

	import org.apache.poi.ss.usermodel.Row;
	import org.bson.Document;

	import com.mongodb.MongoClient;
	import com.mongodb.client.MongoCollection;
	import com.mongodb.client.MongoCursor;
	import com.mongodb.client.MongoDatabase;

	public class BigbasketDatabase 
	{

		public static Object[] readFromMongo(String collectionName) throws IOException 
		{
	        MongoClient mongoclient=new MongoClient("localhost",27017);
			
		    MongoDatabase database=mongoclient.getDatabase("Bigbasket");
		    
		    MongoCollection<Document> collection=database.getCollection(collectionName);
			
	        MongoCursor<Document> cursor=collection.find().iterator();
		    int rows=(int) collection.countDocuments();
		    int columns=2;
		    
		    Object[][] products=new Object[rows][columns];
		    int count=0;
		    while(cursor.hasNext())
		    {
		    	Document d=cursor.next();
		    	
		    	
		    	String productname=d.getString("ProductName");
		    	
		         int quantity=(int) Math.round(d.getDouble("Quantity"));
		        
		        products[count][0]=productname;
		        products[count][1]=quantity;
		        count++;
		        
		                
		    	
		    }
		    
		
			  
			  
		  
		return products;
	    		
	    	
			

		}

	}

