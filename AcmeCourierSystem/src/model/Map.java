package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * a directed graph that holds the geographical area serviced by the company
 */
public class Map implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The array of intersections that are on the map
	 */
	private HashMap<String, Intersection> intersections;
	/**
	 * The array of roads that exist between the intersections on the map
	 */
	private ArrayList<Road> roads; // TODO probably get rid of this since each
									// Intersection tracks its own roads

	/**
	 * returns the route from the specified start intersection to the specified
	 * end intersection
	 * 
	 * @param start
	 * @param end
	 */

	public Map() {
		intersections = new HashMap<String, Intersection>();
		roads = new ArrayList<Road>();
	}

	public Map(HashMap<String, Intersection> intersections, ArrayList<Road> roads) {
		this.intersections = intersections;
		this.roads = roads;
	}

	public Route getRoute(Intersection start, Intersection end) {
		return new Route(this, start, end);
	}

	public HashMap<String, Intersection> getIntersections() {
		return intersections;
	}

	public Intersection getIntersection(String name) {
		return intersections.get(name);
	}

	public void addIntersection(Intersection intersection) {
		intersections.put(intersection.getName(), intersection);
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