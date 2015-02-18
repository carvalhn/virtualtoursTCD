package com.secondspring.db;

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
            rs = stmt.executeQuery("SELECT * FROM mytestdb.vgmuc_formmaker_submits where element_label=1;");
 
            while(rs.next())
            {
                System.out.println("Employee ID="+rs.getInt("id"));

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
        List <Double> latitudeList = new ArrayList<Double>();
        List <Double> longitudeList = new ArrayList<Double>();
        List<Location> locationList = new ArrayList<Location>();
      
        /* retrieves Latitude List for locations and adds to List latitudeList */
      
        rs = stmt.executeQuery("SELECT element_value FROM mytestdb.vgmuc_formmaker_submits where element_label=2 order by group_id");
      
        while(rs.next())
        {
        	Double d=  Double.parseDouble(rs.getString("element_value"));
        	latitudeList.add( d );
        }
      
        /* retrieves Longitude List for locations and adds to List longitudeList */
      
        rs = stmt.executeQuery("SELECT element_value FROM mytestdb.vgmuc_formmaker_submits where element_label=3 order by group_id");
      
        while(rs.next())
        {
    	  Double d=  Double.parseDouble(rs.getString("element_value"));
         longitudeList.add( d );
        }	

        /* Adding Latitude, Longitude for each location to Location object, which is then added to a List of Location iteratively */
        for (int i=1; i< latitudeList.size(); i++)
        {
        	double lat = (double) latitudeList.get(i);
        	double lon = (double) longitudeList.get(i);
        	Location location = new Location();
        	location.setLatitude(lat);
        	location.setLongitude(lon);
        	locationList.add(location);
        }
       /* Closing the Database connection */ 
       return locationList;
      
	}	
}
	
	
