package Dejan_Package_1;

import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PApplet;


public class EarthquakeCityMap extends PApplet
{
	int yellow = color(255, 255, 0);
	int gray = color(150, 150, 150);	
	
	private UnfoldingMap map;
	
	private static final boolean offline = true;
	
	public void setup() {
		size (950, 600, OPENGL);
		map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
		map.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		
		Location valLoc = new Location(-38.14f, -73.03f);
		Location alaskaLoc = new Location(61.02f, -147.65f);
		Location sumatraLoc = new Location(-3.30f, -95.78f);
		Location japanLoc = new Location(38.322f, -142.369f);
		Location kamchatkaLoc = new Location(52.76f, 160.06f);
			
		Feature valEq = new PointFeature(valLoc);
		Feature alaskaEq = new PointFeature(alaskaLoc);		
		Feature sumatraEq = new PointFeature(sumatraLoc);
		Feature japanEq = new PointFeature(japanLoc);
		Feature kamchatkaEq = new PointFeature(kamchatkaLoc);
		
		valEq.addProperty("title", "Valdivia , Chile");
		valEq.addProperty("magnitude", "9.5");
		valEq.addProperty("date", "May 22, 1960");
		valEq.addProperty("year", "1960");
		
		alaskaEq.addProperty("title", "Alaska , Canada");
		alaskaEq.addProperty("magnitude", "9.2");
		alaskaEq.addProperty("date", "March 28, 1964");
		alaskaEq.addProperty("year", "1964");
		
		sumatraEq.addProperty("title", "Sumatra , Sumatra");
		sumatraEq.addProperty("magnitude", "9.1");
		sumatraEq.addProperty("date", "December 26, 2004");
		sumatraEq.addProperty("year", "2004");		
		
		japanEq.addProperty("title", "Honshu , Japan");
		japanEq.addProperty("magnitude", "9.0");
		japanEq.addProperty("date", "March 11, 2011");
		japanEq.addProperty("year", "2011");	
		
		kamchatkaEq.addProperty("title", "Kamchatka , Russia");
		kamchatkaEq.addProperty("magnitude", "9.0");
		kamchatkaEq.addProperty("date", "November 04, 1952");
		kamchatkaEq.addProperty("year", "1952");
		
		List<PointFeature> bigEqs = new ArrayList<PointFeature>();
		bigEqs.add((PointFeature) valEq);
		bigEqs.add((PointFeature) alaskaEq);
		bigEqs.add((PointFeature) sumatraEq);
		bigEqs.add((PointFeature) japanEq);
		bigEqs.add((PointFeature) kamchatkaEq);	
		
		List<Marker> markers = new ArrayList<Marker>();
		for(PointFeature eq: bigEqs){
			markers.add(new SimplePointMarker(eq.getLocation(),eq.getProperties()));
		}
		
		for(Marker mk: markers){
			if(Integer.parseInt((String) mk.getProperty("year")) > 2000){
				mk.setColor(yellow);
			}
			else{
				mk.setColor(gray);
			}
		}
		
		
		map.addMarkers(markers);
		
		//Marker valMk = new SimplePointMarker(valLoc, valEq.getProperties());
		

		
		//map.addMarker(valMk);


		//SimplePointMarker val = new SimplePointMarker(valLoc);
		//map.addMarker(val);
	}	
	
	public void draw(){
		background(10);
		map.draw();
		addkey();
		
	}

	private void addkey() {
		// TODO Auto-generated method stub
		
	}
}