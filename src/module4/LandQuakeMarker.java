package module4;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;

/** Implements a visual marker for land earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class LandQuakeMarker extends EarthquakeMarker {
	
	
	public LandQuakeMarker(PointFeature quake) {
		
		// calling EarthquakeMarker constructor
		super(quake);
		
		// setting field in earthquake marker
		isOnLand = true;
	}
	
	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		
		// Save previous drawing style
		pg.pushStyle();
		
		// TODO: Add code to draw a triangle to represent the CityMarker
		//pg.fill(255, 255, 255);

		if(age.equals("Past Day")) {
		pg.ellipse(x, y, 1.5f*radius, 1.5f*radius);
		pg.line(x-10, y-10, x+10, y+10);
		pg.line(x-10, y+10, x+10, y-10);
		}
		
		else {
			pg.ellipse(x, y, 1.5f*radius, 1.5f*radius);
		}
		
		
		// Restore previous drawing style
		pg.popStyle();
		
		
		// Draw a centered circle for land quakes
		// DO NOT set the fill color here.  That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered circle.
		
		// HINT: Notice the radius variable in the EarthquakeMarker class
		// and how it is set in the EarthquakeMarker constructor
		
		// TODO: Implement this method
		
	}
	

	// Get the country the earthquake is in
	public String getCountry() {
		return (String) getProperty("country");
	}



		
}