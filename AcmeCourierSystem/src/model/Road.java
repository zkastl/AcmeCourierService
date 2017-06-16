package model;

import java.io.Serializable;

/**
 * an edge in the map graph. the connection between two intersections
 */
@SuppressWarnings("serial")
public class Road implements Serializable {

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
	
	// the distance between start and end
	private Integer length;
	
	public Road(Intersection start, Intersection end, String dir, String name, Integer len) {
		this.start = start;
		this.end = end;
		this.direction = dir;
		this.name = name;
		this.length = len;
	}
	
	public Road(Intersection start, Intersection end, String dir, String name) {
		this.start = start;
		this.end = end;
		this.direction = dir;
		this.name = name;
		this.length = 1;
	}
	
	public Road(Intersection start, Intersection end, String dir) {
		this.start = start;
		this.end = end;
		this.direction = dir;
		this.name = ((dir.compareTo("North") == 0 || dir.compareTo("South") == 0) ? start.getStreet() : start.getAve()) /*start.getName() + "to" + end.getName()*/;
		this.length = 1;
	}
	
	public Intersection getStart() {
		return start;
	}
	public void setStart(Intersection intersection) {
		this.start = intersection;
	}
	public Intersection getEnd() {
		return end;
	}
	public void setEnd(Intersection intersection) {
		this.end = intersection;
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