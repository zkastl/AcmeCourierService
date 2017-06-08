package model;

import java.util.ArrayList;

/**
 * a directed graph that holds the geographical area serviced by the company
 */
public class Map {

	/**
	 * The array of intersections that are on the map
	 */
	private ArrayList<Intersection> intersections;
	/**
	 * The array of roads that exist between the intersections on the map
	 */
	private ArrayList<Road> roads;

	/**
	 * returns the route from the specified start intersection to the specified
	 * end intersection
	 * 
	 * @param start
	 * @param end
	 */
	public Route getRoute(Intersection start, Intersection end) {
		return new Route(this, start, end);
	}
	
	public ArrayList<Intersection> getIntersections() {
		return intersections;
	}
	public void addIntersection(Intersection intersection) {
		intersections.add(intersection);
	}
	public void removeIntersection(Intersection intersection) {
		intersections.remove(intersection);
	}
	
	public ArrayList<Road> getRoads() {
		return roads;
	}
	public void addRoad(Road road) {
		roads.add(road);
	}
	public void removeRoad(Road road) {
		roads.remove(road);
	}
	
	public void clear() {
		intersections.clear();
		roads.clear();
	}

}