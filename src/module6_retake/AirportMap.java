package module6_retake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.geo.Location;
import parsing.ParseFeed;
import processing.core.PApplet;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;



/** An applet that shows airports (and routes)
 * on a world map.  
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMap extends PApplet {
	
	UnfoldingMap map;
	private List<Marker> airportList;
	List<Marker> routeList;
	private CommonMarker lastClicked = null;
	private CommonMarker lastSelected = null;
	
	private static final boolean offline = false;

/*	String url = "jdbc:sqlserver://MYPC\\SQLEXPRESS;databaseName=MYDB;integratedSecurity=true";
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	Connection conn = DriverManager.getConnection(url);
	*/
	
	public void setup() {
		// setting up PAppler
		size(800,600, OPENGL);
		
		if (offline) {
			;//map = new UnfoldingMap(this, 200, 50, 650, 600, new MBTilesMapProvider(mbTilesString));

		}
		else {
		// setting up map and default events
		map = new UnfoldingMap(this, 0, 0, 950, 750);
		MapUtils.createDefaultEventDispatcher(this, map);
		}
		// get features from airport data
		List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
		for(PointFeature feature : features) {
		//System.out.println(feature.getProperties());
			//System.out.println(feature.getId());
		}
		
				// list for markers, hashmap for quicker access when matching with routes
		airportList = new ArrayList<Marker>();
		HashMap<Integer, Location> airports = new HashMap<Integer, Location>();
		
		
		
		// create markers from features
		for(PointFeature feature : features) {
			AirportMarker m = new AirportMarker(feature);
			m.setRadius(5);
			//m.showTitle(pg, x, y);
			//System.out.println(m.getAirportId());
			airportList.add(m);//WE'RE ADDING CHILD TYPE OBJECTS TO A PARENT TYPE ARRAY
			
			// put airport in hashmap with OpenFlights unique id for key
			airports.put(Integer.parseInt(feature.getId()), feature.getLocation());//NOT SURE WHAT THIS DOES?
		
			AirportMarker airportsArray [];
			
			
		}
		
/*		for(Marker airport : airportList){
			System.out.println(airport.getId());
		}*/

		
		
		//"AIRPORTS" IS HASHMAP OF AIRPORT ID'S AND LOCATION
		for (Integer name: airports.keySet()){
            String key = name.toString();
            String value = airports.get(name).toString();  
            //System.out.println(key + "      " + value);  
		} 
		
		//"AIRPORTLIST" IS A LIST OF AIRPORT PROPERTIES WITH ID AS FIRST FIELD
/*		for (Marker airport : airportList){
			System.out.println(airport.getId());
		}	*/
		
		// parse route data
		List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");
		
		//"ROUTES" IS LIST OF SOURCE ID AND DESTINATION ID
		for (ShapeFeature route : routes){
			//System.out.println(route.getProperties());
		}	
		/*
		for (PointFeature feature : features){
			System.out.println(feature.getProperties());
		}*/
		
		routeList = new ArrayList<Marker>();
		for(ShapeFeature route : routes) {
			
			// get source and destination airportIds
			int source = Integer.parseInt((String)route.getProperty("source"));//HOW DO WE KNOW WHAT PROPERTY IS CALLED?//WE'RE TELLING THE COMPILER WHAT IT GETS - HOW ELSE DOES IT KNOW?
			int dest = Integer.parseInt((String)route.getProperty("destination"));//WE'RE TELLING THE COMPILER WHAT IT GETS - HOW ELSE DOES IT KNOW?
			
			// get locations for airports on route
			if(airports.containsKey(source) && airports.containsKey(dest)) {
				route.addLocation(airports.get(source));
				route.addLocation(airports.get(dest));
			}
			
			SimpleLinesMarker sl = new SimpleLinesMarker(route.getLocations(), route.getProperties());
		
	//		System.out.println(sl.getProperties());
			
			//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
			routeList.add(sl);//WE"RE ADDING CHILD TYPE OBJECTS TO A PARENT TYPE ARRAY
			

			
		}
		
		
		
		//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
		map.addMarkers(routeList);
		
		map.addMarkers(airportList);
	
		for(Marker route : routeList){
			route.setHidden(true);
		}
		
		//System.out.print((CommonMarker) airportList.get(0).showTitle());
		
	}	
	
	public void draw() {
		background(0);
		map.draw();
		addKey();
	}
	
	public void addKey(){
		fill(238, 252, 168);
		rect(10, 10, 250, 70);	
		fill(0, 0, 0);
		text("Height below 500m", 10, 25);
		text("Height between 500m and 1000m", 10, 40);
		text("Height below 1000m and 1500m", 10, 55);
		text("Height above 1500m", 10, 70);
		fill(255, 0, 0);
		rect(225, 15, 10, 10);
		fill(0, 255, 0);
		rect(225, 30, 10, 10);
		fill(0, 0, 255);
		rect(225, 45, 10, 10);
		fill(125, 125, 125);
		rect(225, 60, 10, 10);
	}
	
	public void mouseClicked() {
		if(lastClicked == null){
			if(isWithinAirport()){
				hideRemainingAirports();
				showAirportRoutes();
			}
		}
		
		else if(lastClicked != null){
			showAllAirports();
			hideAirportRoutes();
			lastClicked = null;
		}
	}
	
	public void mouseMoved() {
		for(Marker airport: airportList){
			if(airport.isInside(map, mouseX, mouseY)){
				if(lastSelected != null) {
					lastSelected.setSelected(false);
				}
				airport.setSelected(true);
				lastSelected = (CommonMarker) airport;
				break;
			}
		}
	}
	
	
	
	public boolean isWithinAirport(){
		for(Marker airport : airportList){
			if(airport.isInside(map, mouseX, mouseY)){
				lastClicked = (CommonMarker) airport;
				((CommonMarker) airport).setClicked(true);
				return true;
			}
		}
		return false;
	}
	
	public void hideRemainingAirports(){
		for(Marker airport : airportList){
			if(airport != lastClicked){
				airport.setHidden(true);
			}
		}
	}
	
	public void showAllAirports(){
		for(Marker airport : airportList){
			airport.setHidden(false);	
		}
	}
	
	public void showAirportRoutes(){
		for(Marker route : routeList){
			//for(Marker airport : airportList){
				//System.out.println(route.getProperty("source") + " " + route.getProperty("destination") + "End");
				if(route.getProperty("source").equals(((AirportMarker) lastClicked).getAirportId())/*route.getProperty("source") == ((AirportMarker) airport).getAirportId()*/){	
					//System.out.println(airport.getAirportId());
					//System.out.println(route.getProperty("source"));
					route.setHidden(false);
					System.out.println("Testing");
			//	}
				}
		}
	}
	
	public void hideAirportRoutes(){
		for(Marker route : routeList){
			route.setHidden(true);
		}
	}
	
	

}
