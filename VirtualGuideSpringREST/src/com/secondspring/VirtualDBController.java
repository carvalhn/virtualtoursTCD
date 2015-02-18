package com.secondspring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RestController;

import com.secondspring.db.*;

/* This Controller handles request which are forwarded from the Dispatcher Servlet */
@Controller
public class VirtualDBController {
	
	/* Code to get all locations when allLocations is present at the end of URL */
	
	@RequestMapping("/allLocations")
	@ResponseBody
    public Locations getLocationList() throws Exception {
		
		/* creates the DB Connection and retrieves locations through Query on mySQL - testdb in current system */
		
		VirtualGuideDBConnect virtualGuideDBConnect= new VirtualGuideDBConnect();			
		List<Location> allLocations= new ArrayList<Location>();
		allLocations=virtualGuideDBConnect.getAllLocations();
       
		/* To test if REST returns object without DB connection
		Location mockLocation1=new Location();
		mockLocation1.setLatitude((int)10.98234324);
		mockLocation1.setLongitude((int)9.02323);
		Location mockLocation2=new Location();
		mockLocation2.setLatitude((int)134.66242);
		mockLocation2.setLongitude((int)3.2243243);
		List<Location>  location=new ArrayList<Location>();
		location.add(mockLocation1);
		location.add(mockLocation2);
		*/
		
		/* returns a Locations object whose class variable contains the list of Lat,Long for all Locations */
		
		Locations geoLocations=new Locations();
		geoLocations.setLocation(allLocations);
        return geoLocations;
	}
	
}
