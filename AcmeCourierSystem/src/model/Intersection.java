package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import main.CourierSystem;

/**
 * a node in the map graph. represents the intersection of two streets
 */
// is not consistent with equals
// only designed to work for streets that only intersect once
@Entity(name="CityMap")
public class Intersection implements Comparable<Intersection>, Serializable {

	
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	/**
	 * The name of the north-south street of an intersection
	 */
	private String street;
	/**
	 * The name of the east-west street of an intersection
	 */
	private String avenue;
	/**
	 * The first day of the most recent closure of the intersection, must be <=
	 * closeEnd, initialize to 1/1/17
	 */
	private LocalDate closeStart;
	/**
	 * the last day of the most recent closure of the intersection, must be >=
	 * closeStart, initialize to 1/1/17
	 */
	private LocalDate closeEnd;
	/**
	 * an array of the roads that start at the intersection (the edges that
	 * leave the node)
	 */
	private ArrayList<Road> roads;

	// distance from the start node to this node
	private Integer distance;

	// previous intersection in the calculated path
	private String previous;
	
	@Transient
	private Intersection truePrevious;

	// whether or not the node has been visited yet in the calculation
	private boolean visited;

	public Intersection(String st, String ave) {
		street = st;
		avenue = ave;
		closeStart = LocalDate.of(2017, 1, 1);
		closeEnd = LocalDate.of(2017, 1, 1);
		roads = new ArrayList<Road>();
		distance = 0;
		previous = "";
		truePrevious = null;
		visited = false;
	}

	public Intersection(String st, String ave, ArrayList<Road> roads) {
		street = st;
		avenue = ave;
		closeStart = LocalDate.of(2017, 1, 1);
		closeEnd = LocalDate.of(2017, 1, 1);
		this.roads = roads;
		distance = 0;
		truePrevious = null;
		visited = false;
	}

	public Intersection(String st, String ave, ArrayList<Road> roads, LocalDate closeStart, LocalDate closeEnd) {
		street = st;
		avenue = ave;
		this.closeStart = closeStart;
		this.closeEnd = closeEnd;
		this.roads = roads;
		distance = 0;
		previous = "";
		truePrevious = null;
		visited = false;
	}
	
	public Intersection() {
		// Method required for JPA persistence, do not delete.
	}

	public String getStreet() {
		return street;
	}

	public String getAve() {
		return avenue;
	}

	public String getName() {
		return street + avenue;
	}

	public LocalDate getCloseStart() {
		return closeStart;
	}

	public LocalDate getCloseEnd() {
		return closeEnd;
	}

	public ArrayList<Road> getRoads() {
		return roads;
	}

	public void addRoad(Road road) {
		roads.add(road);
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer dist) {
		distance = dist;
	}

	public Intersection getPrevious() {
		return truePrevious;
	}

	public void setPrevious(Intersection prev) {
		previous = prev.getName();
		truePrevious = prev;
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
		LocalDate today = LocalDate.now();
		
		if (today.isBefore(closeStart) || today.isAfter(closeEnd)) {
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
	public void changeClosure(LocalDate start, LocalDate end) {
		if (end.isBefore(start)) {
			// error message
			System.out.println("closeEnd cannot be before closeStart");
		} else {
			closeStart = start;
			closeEnd = end;
		}
	}

	// allows intersections to be used in data structures that require the
	// comparable interface such as a priority queue
	// is not consistent with equals
	@Override
	public int compareTo(Intersection rhs) {
		if (rhs == null) {
			throw new NullPointerException();
		}

		if (equals(rhs)) { // they are the same intersection
			return 0;
		} else if (distance < rhs.distance) { // this intersection currently has
												// a shorter path length
			return -1;
		} else if (distance > rhs.distance) { // the rhs intersection currently
												// has a shorter path length
			return 1;
		}
		return 0; // they have the same path length, but are not the same
					// intersection
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true; // they are the same object
		}
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false; // o is not an Intersection
		}
		Intersection rhs = (Intersection) o;
		return Objects.equals(street, rhs.street) && Objects.equals(avenue,
				rhs.avenue)/*
							 * && Objects.equals(roads, rhs.roads)
							 */; // don't want road and intersection equals
								// functions to loop
	}

	@Override
	public int hashCode() {
		return Objects.hash(street, avenue, roads);
	}

	public void setAve(String ave) {
		avenue = ave;
	}

	public void setStreet(String st) {
		street = st;
	}

}