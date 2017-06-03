package model;

/**
 * a directed graph that holds the geographical area serviced by the company
 */
public class Map {

	/**
	 * The array of intersections that are on the map
	 */
	private Intersection[] intersections;
	/**
	 * The array of roads that exist between the intersections on the map
	 */
	private Road[] roads;

	/**
	 * returns the route from the specified start intersection to the specified
	 * end intersection
	 * 
	 * @param start
	 * @param end
	 */
	public Route getRoute(Intersection start, Intersection end) {
		// TODO - implement Map.getRoute
		throw new UnsupportedOperationException();
	}

}