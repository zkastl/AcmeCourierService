package model;

import java.util.Date;

/**
 * a node in the map graph. represents the intersection of two streets
 */
public class Intersection {

	/**
	 * The name of the east-west street of an intersection
	 */
	private String street1;
	/**
	 * The name of the north-south street of an intersection
	 */
	private String street2;
	/**
	 * The first day of the most recent closure of the intersection, must be <=
	 * closeEnd, initialize to 1/1/17
	 */
	private Date closeStart;
	/**
	 * the last day of the most recent closure of the intersection, must be >=
	 * closeStart, initialize to 1/1/17
	 */
	private Date closeEnd;
	/**
	 * an array of the roads that start at the intersection (the edges that
	 * leave the node)
	 */
	private Road[] roads;

	/**
	 * returns true if closeEnd is in the past, or closeStart is in the future
	 */
	public boolean isOpen() {
		// TODO - implement Intersection.isOpen
		throw new UnsupportedOperationException();
	}

	/**
	 * checks if the new start and end dates passed in are valid, if so, sets
	 * closeStart to start, and closeEnd to end
	 * 
	 * @param start
	 * @param end
	 */
	public void changeClosure(Date start, Date end) {
		// TODO - implement Intersection.changeClosure
		throw new UnsupportedOperationException();
	}

}