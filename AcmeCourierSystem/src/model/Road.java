package model;

/**
 * an edge in the map graph. the connection between two intersections
 */
public class Road {

	/**
	 * The intersection the road starts from
	 */
	private Intersection start;
	/**
	 * the intersection the road goes to
	 */
	private Intersection end;
	/**
	 * the direction the road goes (North, South, East, West)
	 */
	private String direction;
	/**
	 * the name of the road
	 */
	private String name;

}