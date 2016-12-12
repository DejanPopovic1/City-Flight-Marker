package main_package;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class EthnicMap extends PApplet {
    UnfoldingMap map;

	private static final boolean offline = true;
    
    public void settings() {
        size(1800, 1600);
    }


    public void setup() {
    	map = new UnfoldingMap(this, 50, 50, 700, 500);
        //map.zoomToLevel(2);
       // map.setBackgroundColor(240);
        //MapUtils.createDefaultEventDispatcher(this, map);
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{EthnicMap.class.getName()});

System.out.print("Hello World");
    }

    public void draw() {
        background(240);

        // Draw map tiles and country markers
        map.draw();
    }
 

}
