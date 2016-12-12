package module6_retake;

import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PGraphics;

/** 
 * A class to represent AirportMarkers on a world map.
 *   
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMarker extends CommonMarker {
	public static List<SimpleLinesMarker> routes;
	public String airportId;
	
	
	
	public AirportMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
		this.airportId = city.getId();
	}
	
/*	public void draw(processing.core.PGraphics pg, float x, float y){
		;
	}*/
	
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		Integer altitude = Integer.parseInt((String) this.getProperty("altitude"));
		if(altitude < 500) {
			pg.fill(255,0,0);
		}
		else if (altitude >= 500 && altitude < 1000){
			pg.fill(0, 255, 0);
		}
		else if (altitude >= 1000 && altitude < 1500){
			pg.fill(0, 0, 255);
		}
		else {
			pg.fill(125, 125, 125);
		}
		pg.ellipse(x, y, 5, 5);	
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		 // show rectangle with title
		pg.fill(238, 252, 168);
		pg.rect(x, y, pg.textWidth((String)this.getProperty("name")) + 2, 20);
		pg.fill(0, 0, 0);
		pg.text(" " + (String)this.getProperty("name"), x, y + 10);
		// show routes
		
		
	}
	
	public String getAirportId() {
		return this.airportId;
	}
	
}
