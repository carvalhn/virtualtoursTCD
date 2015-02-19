package com.secondspring.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.secondspring.Location;

public class VirtualGuideDBConnect
{
		
		/*This function tries to establish a connection to the database and returns the connection */
	
	public Connection getDataSource() throws Exception
	{	
		InitialContext cxt = new InitialContext();
		if ( cxt == null )
		{
		   throw new Exception("Uh oh -- no context!");
		}

		DataSource ds = (DataSource) cxt.lookup( "java:/MySqlDS" );

		if ( ds == null ) 
		{
		   throw new Exception("Data source not found!");
		}
		
		Connection con = null;
		        
		try
		{
	        con = ds.getConnection();
	        System.out.println("Succesfully connects to DB");
        }
		catch (SQLException e) 
		{
		            e.printStackTrace();
        }
        return con;
		
	}

	/* function that uses connection created above, queries database, retrieves Locations and assigns it to a List of Location objects */ 	
	
	public List<Location> getAllLocations() throws Exception
	{
		Connection con= getDataSource();
        Statement stmt = null;
        ResultSet rs = null;
        System.out.println("Connection object retrieved succesfully");
        try 
        {    
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Latitude FROM mytestdb.vgmuc_locations where ID=1;");
 
            while(rs.next())
            {
                System.out.println("Latitude ="+rs.getBigDecimal("ID"));

             }
        } 
        catch (SQLException e) 
        {
        		e.printStackTrace();
        }
//        finally
//        {    
//        	try 
//        	{
//                if(rs != null) rs.close();
//                if(stmt != null) stmt.close();
//                if(con != null) con.close();
//            } 
//        	catch (SQLException e) 
//        	{
//                e.printStackTrace();
//            }
//        }
//        
        BigDecimal d = BigDecimal.ZERO;
        List <BigDecimal> latitudeList = new ArrayList<BigDecimal>();
        List <BigDecimal> longitudeList = new ArrayList<BigDecimal>();
        List <String> touristCentreLocationNameList = new ArrayList<String>();
        List <Location> locationList = new ArrayList<Location>();
      
        /* retrieves Latitude List for locations and adds to List latitudeList */
      
        rs = stmt.executeQuery("Select Latitude FROM mytestdb.vgmuc_locations order by ID");
        while(rs.next())
        {
        	d=  rs.getBigDecimal("Latitude");
        //	System.out.println("\n Getting inside Lat iterator correctly");
        	latitudeList.add(d);
        }
      
        /* retrieves Longitude List for locations and adds to List longitudeList */
      
        rs = stmt.executeQuery("SELECT Longitude FROM mytestdb.vgmuc_locations order by ID");
        while(rs.next())
        {
    	 d = rs.getBigDecimal("Longitude");
         longitudeList.add(d);
        }	
        
        rs = stmt.executeQuery("SELECT Tourist_Centre_Location_Name FROM mytestdb.vgmuc_locations order by ID");
        while(rs.next())
        {
    	 String a = rs.getString("Tourist_Centre_Location_Name");
    	 touristCentreLocationNameList.add(a);
        }
        
        /* Adding Latitude, Longitude for each location to Location object, which is then added to a List of Location iteratively */
        
        for (int i=1; i< latitudeList.size(); i++)
        {
        	BigDecimal lat = latitudeList.get(i);
        	BigDecimal lon = longitudeList.get(i);
        	String locationName = touristCentreLocationNameList.get(i);
        	Location location = new Location();
        	location.setLatitude(lat);
        	location.setLongitude(lon);
        	location.setTouristCentreLocationName(locationName);
        	locationList.add(location);
        }
       /* Closing the Database connection */ 
      System.out.println("Reaching here correctly");
        return locationList;
      
	}	
}
	
	
