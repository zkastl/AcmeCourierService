package model;

import java.io.Serializable;

import main.CourierSystem;

/**
 * an edge in the map graph. the connection between two intersections
 */

public class Road implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The intersection the road starts from
	 */
	private String start;
	/**
	 * the intersection the road goes to
	 */
	private String end;
	/**
	 * the direction the road goes (North, South, East, West)
	 */
	private String direction;
	/**
	 * the name of the road
	 */
	private String name;

	// the distance between start and end
	private Integer length;

	public Road(Intersection start, Intersection end, String dir, String name, Integer len) {
		this.start = start.getName();
		this.end = end.getName();
		this.direction = dir;
		this.name = name;
		this.length = len;
	}

	public Road(Intersection start, Intersection end, String dir, String name) {
		this.start = start.getName();
		this.end = end.getName();
		this.direction = dir;
		this.name = name;
		this.length = 1;
	}

	public Road(Intersection start, Intersection end, String dir) {
		this.start = start.getName();
		this.end = end.getName();
		this.direction = dir;
		this.name = ((dir.compareTo("North") == 0 || dir.compareTo("South") == 0) ? start.getStreet()
				: start.getAve()) /* start.getName() + "to" + end.getName() */;
		this.length = 1;
	}

	public Intersection getStart() {
		return CourierSystem.CityMap.getIntersection(start);
	}

	public void setStart(Intersection intersection) {
		this.start = intersection.getName();
	}

	public Intersection getEnd() {
		return CourierSystem.CityMap.getIntersection(end);
	}

	public void setEnd(Intersection intersection) {
		this.end = intersection.getName();
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String dir) {
		direction = dir;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer len) {
		length = len;
	}

}