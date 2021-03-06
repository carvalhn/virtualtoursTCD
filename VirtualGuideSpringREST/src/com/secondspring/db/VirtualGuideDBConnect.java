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
import com.secondspring.LocationData;

public class VirtualGuideDBConnect
{
		
		/*This function tries to establish a connection to the database and returns the connection */
	
	String dbTable="vgmuc_locations";
	
	/*This variable is used in getLocationsForCurrentLocation() to specify the radius within which locations have to be obtained */
	
	int distance = 30;
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
        System.out.println("Connection object retrieved succesfully for getAllLocations");
        try 
        {    
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Latitude FROM" +dbTable);
            while(rs.next())
            {
                System.out.println("Latitude ="+rs.getBigDecimal("Latitude"));

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
      
        rs = stmt.executeQuery("Select Latitude FROM vgmuc_locations order by ID");
        while(rs.next())
        {
        	d=  rs.getBigDecimal("Latitude");
        //	System.out.println("\n Getting inside Lat iterator correctly");
        	latitudeList.add(d);
        }
      
        /* retrieves Longitude List for locations and adds to List longitudeList */
      
        rs = stmt.executeQuery("SELECT Longitude FROM vgmuc_locations order by ID");
        while(rs.next())
        {
    	 d = rs.getBigDecimal("Longitude");
         longitudeList.add(d);
        }	
        
        rs = stmt.executeQuery("SELECT Tourist_Centre_Location_Name FROM vgmuc_locations order by ID");
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
	
	public List<Location> getLocationsForCurrentLocation(int ID) throws Exception
	{
		Connection con= getDataSource();
        Statement stmt = null;
        ResultSet rs = null;
        BigDecimal currentLatitude = BigDecimal.ZERO;
        BigDecimal currentLongitude = BigDecimal.ZERO;
        BigDecimal radCurrentLatitude = BigDecimal.ZERO;
        BigDecimal radCurrentLongitude = BigDecimal.ZERO;
        System.out.println("Connection object retrieved succesfully for getLocationsForCurrentLocation");
        try 
        {    
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Latitude,Longitude FROM "+dbTable+" where ID="+ID);
            currentLatitude=rs.getBigDecimal("Latitude");
            currentLongitude=rs.getBigDecimal("Longitude");
            BigDecimal pi = new BigDecimal(Math.PI);
            BigDecimal temp = pi.multiply(currentLatitude); 
            radCurrentLatitude = temp.divide(new BigDecimal(180));
            BigDecimal temp2 = pi.multiply(currentLongitude); 
            radCurrentLongitude = temp2.divide(new BigDecimal(180));            
        } 
        
        catch (SQLException e) 
        {
        		e.printStackTrace();
        }   
        
        BigDecimal d = BigDecimal.ZERO;
 //       BigDecimal d2 = BigDecimal.ZERO;
        List <BigDecimal> latitudeList = new ArrayList<BigDecimal>();
        List <BigDecimal> longitudeList = new ArrayList<BigDecimal>();
        List <String> touristCentreLocationNameList = new ArrayList<String>();
        List <Location> locationList = new ArrayList<Location>();
        
        /* Query to retrieve locations within a distance specified by class variable "distance" */ 
        
        
        rs = stmt.executeQuery("SELECT Latitude,Longitude,Tourist_Centre_Location_Name, (3959 * acos (cos ( "+radCurrentLatitude.toString()+" )* cos( radians( Latitude ) ) * cos( radians( Longitude ) - "+radCurrentLongitude.toString()+" ) + sin ( "+radCurrentLatitude.toString()+" ) * sin( radians( Latitude ) ) ) ) AS distance FROM "+dbTable+" HAVING distance < "+distance);
         
        /* retrieves Latitude and Longitudes for each location and adds to List latitudeList and LongitudeList */      
       
        while(rs.next())
        {
        	d =  rs.getBigDecimal("Latitude");
        //	System.out.println("\n Getting inside Latitude iterator correctly");
        	latitudeList.add(d);
        	d = rs.getBigDecimal("Longitude");
        	longitudeList.add(d);
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
	
	/*This function retrieves Tourist_Centre_Location_Name,Tourist_Building_Name,Latitude,Longitude,Audio_Links,Image_Links from table */

	public LocationData getDataForCurrentLocation(int id) throws Exception
	{
		Connection con= getDataSource();
        Statement stmt = null;
        ResultSet rs = null;
        String touristCentreLocationName="";
        String touristBuildingName="";
        BigDecimal latitude=BigDecimal.ZERO;
        BigDecimal longitude=BigDecimal.ZERO;
        LocationData locationData = new LocationData();
        String[] audioLinks = new String[250];
        
        String[] imageLinks = new String[250];
        String text="";
        System.out.println("Connection object retrieved succesfully for getDataForCurrentLocation");
        try 
        {    
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Tourist_Centre_Location_Name,Tourist_Building_Name,Text,Latitude,Longitude,Audio_Links,Image_Links FROM "+dbTable+" where ID = "+id);
            if(rs.next())
            {
            	
            touristCentreLocationName=rs.getString("Tourist_Centre_Location_Name");
            System.out.println(touristCentreLocationName);
            touristBuildingName=rs.getString("Tourist_Building_Name");
            System.out.println(touristBuildingName);
            latitude=rs.getBigDecimal("Latitude");
            longitude=rs.getBigDecimal("Longitude");
            String a=rs.getString("Audio_Links");
            String i=rs.getString("Image_Links");
            text=rs.getString("Text");
            audioLinks = a.split(";");
            imageLinks = i.split(";");            
            }
        } 
        catch (SQLException e) 
        {
        		e.printStackTrace();
        }
       
        locationData.setLocationName(touristCentreLocationName);
        locationData.setLatitude(latitude);
        locationData.setLongitude(longitude);
        locationData.setText(text);
        locationData.setTouristBuildingName(touristBuildingName);
        locationData.setAudioLinks(audioLinks);
        locationData.setImageLinks(imageLinks);
        System.out.println("Reaching end correctly");
        return locationData;
      
	}	
	
	

}
	
	
