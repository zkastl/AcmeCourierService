package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

/**
 * a node in the map graph. represents the intersection of two streets
 */
// is not consistent with equals
// only designed to work for streets that only intersect once
public class Intersection implements Comparable<Intersection>{

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
	private Calendar closeStart;
	/**
	 * the last day of the most recent closure of the intersection, must be >=
	 * closeStart, initialize to 1/1/17
	 */
	private Calendar closeEnd;
	/**
	 * an array of the roads that start at the intersection (the edges that
	 * leave the node)
	 */
	private ArrayList<Road> roads;
	
	// distance from the start node to this node
	private Integer distance;
	
	// previous intersection in the calculated path
	private Intersection previous;
	 // whether or not the node has been visited yet in the calculation
	private boolean visited;
	
	public String getStreet1() {
		return street1;
	}
	public String getStreet2() {
		return street2;
	}
	public String getName() {
		return street1+street2;
	}
	public Calendar getCloseStart() {
		return closeStart;
	}
	public Calendar getCloseEnd() {
		return closeEnd;
	}
	public ArrayList<Road> getRoads() {
		return roads;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer dist) {
		distance = dist;
	}
	public Intersection getPrevious() {
		return previous;
	}
	public void setPrevious(Intersection prev) {
		previous = prev;
	}
	public boolean getVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * returns true if closeEnd is in the past, or closeStart is in the future
	 */
	public boolean isOpen() {
		Calendar today = Calendar.getInstance();
		//today.setTime(new Date()); //shouldn't be necessary
		if(today.before(closeStart) || today.after(closeEnd)) {
			return true;
		}
		return false;
	}

	/**
	 * checks if the new start and end dates passed in are valid, if so, sets
	 * closeStart to start, and closeEnd to end
	 * 
	 * @param start
	 * @param end
	 */
	public void changeClosure(Calendar start, Calendar end) {
		if(end.before(start)) {
			//error message
		} else {
			closeStart = start;
			closeEnd = end;
		}
	}
	

	// allows intersections to be used in data structures that require the comparable interface such as a priority queue
	// is not consistent with equals
	@Override
	public int compareTo(Intersection rhs) {
		if(rhs == null) {
			throw new NullPointerException();
		}
		
		if(getName().compareTo(rhs.getName()) == 0) {	// they are the same intersection
		  return 0;
		} else if(distance < rhs.distance) {	// arg0 currently has a shorter path length
			return -1;
		} else if(distance > rhs.distance) { 	// arg1 currently has a shorter path length
			return 1;
		}
		return 0;								// they have the same path length, but are not the same intersection
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true; // they are the same object
		}
		if(o == null) {
			return false;
		}
		if(getClass() != o.getClass()) {
			return false; // o is not an Intersection
		}
		Intersection rhs = (Intersection) o;
		return Objects.equals(street1, rhs.street1) &&
				Objects.equals(street2, rhs.street2)/* &&
				Objects.equals(roads, rhs.roads)*/;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(street1, street2, roads);
	}

}