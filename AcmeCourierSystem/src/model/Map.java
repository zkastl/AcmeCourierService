package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.*;
import com.sun.istack.internal.NotNull;

/**
 * a directed graph that holds the geographical area serviced by the company
 */
@Entity(name="CityMap")
public class Map implements Serializable {

	/**
	 * 
	 */
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long mapId;
	
	private static final long serialVersionUID = 1L;
	/**
	 * The array of intersections that are on the map
	 */
	@NotNull
	private HashMap<String, Intersection> intersections;

	public Map() {
		intersections = new HashMap<String, Intersection>();
		//roads = new ArrayList<Road>();
		initMap();
	}

	public Map(HashMap<String, Intersection> intersections, ArrayList<Road> roads) {
		this.intersections = intersections;
		//this.roads = roads;
	}
	
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

	public HashMap<String, Intersection> getIntersections() {
		return intersections;
	}

	public Intersection getIntersection(String name) {
		return intersections.get(name);
	}

	public void addIntersection(Intersection intersection) {
		intersections.put(intersection.getName(), intersection);
	}
	
	public void setIntersections(HashMap<String, Intersection> intersections) {
		this.intersections = intersections;
	}

	public void removeIntersection(Intersection intersection) {
		intersections.remove(intersection);
	}

	public void clear() {
		intersections.clear();
	}

	public void initMap() {
		Intersection a1 = new Intersection("A", "1");
		Intersection a2 = new Intersection("A", "2");
		Intersection a3 = new Intersection("A", "3");
		Intersection a4 = new Intersection("A", "4");
		Intersection a5 = new Intersection("A", "5");
		Intersection a6 = new Intersection("A", "6");
		Intersection a7 = new Intersection("A", "7");

		Intersection b1 = new Intersection("B", "1");
		Intersection b2 = new Intersection("B", "2");
		Intersection b3 = new Intersection("B", "3");
		Intersection b4 = new Intersection("B", "4");
		Intersection b5 = new Intersection("B", "5");
		Intersection b6 = new Intersection("B", "6");
		Intersection b7 = new Intersection("B", "7");

		Intersection c1 = new Intersection("C", "1");
		Intersection c2 = new Intersection("C", "2");
		Intersection c3 = new Intersection("C", "3");
		Intersection c4 = new Intersection("C", "4");
		Intersection c5 = new Intersection("C", "5");
		Intersection c6 = new Intersection("C", "6");
		Intersection c7 = new Intersection("C", "7");

		Intersection d1 = new Intersection("D", "1");
		Intersection d2 = new Intersection("D", "2");
		Intersection d3 = new Intersection("D", "3");
		Intersection d4 = new Intersection("D", "4");
		Intersection d5 = new Intersection("D", "5");
		Intersection d6 = new Intersection("D", "6");
		Intersection d7 = new Intersection("D", "7");

		Intersection e1 = new Intersection("E", "1");
		Intersection e2 = new Intersection("E", "2");
		Intersection e3 = new Intersection("E", "3");
		Intersection e4 = new Intersection("E", "4");
		Intersection e5 = new Intersection("E", "5");
		Intersection e6 = new Intersection("E", "6");
		Intersection e7 = new Intersection("E", "7");

		Intersection f1 = new Intersection("F", "1");
		Intersection f2 = new Intersection("F", "2");
		Intersection f3 = new Intersection("F", "3");
		Intersection f4 = new Intersection("F", "4");
		Intersection f5 = new Intersection("F", "5");
		Intersection f6 = new Intersection("F", "6");
		Intersection f7 = new Intersection("F", "7");

		Intersection g1 = new Intersection("G", "1");
		Intersection g2 = new Intersection("G", "2");
		Intersection g3 = new Intersection("G", "3");
		Intersection g4 = new Intersection("G", "4");
		Intersection g5 = new Intersection("G", "5");
		Intersection g6 = new Intersection("G", "6");
		Intersection g7 = new Intersection("G", "7");

		a1.addRoad(new Road(a1, a2, "South"));
		a2.addRoad(new Road(a2, b2, "East"));
		a2.addRoad(new Road(a2, a3, "South"));
		a3.addRoad(new Road(a3, a4, "South"));
		a4.addRoad(new Road(a4, b4, "East"));
		a4.addRoad(new Road(a4, a5, "South"));
		a5.addRoad(new Road(a5, a6, "South"));
		a6.addRoad(new Road(a6, b6, "East"));
		a6.addRoad(new Road(a6, a7, "South"));

		b1.addRoad(new Road(b1, a1, "West"));
		b2.addRoad(new Road(b2, b1, "North"));
		b2.addRoad(new Road(b2, c2, "East"));
		b2.addRoad(new Road(b2, a2, "West"));
		b3.addRoad(new Road(b3, b2, "North"));
		b3.addRoad(new Road(b3, a3, "West"));
		b4.addRoad(new Road(b4, b3, "North"));
		b4.addRoad(new Road(b4, c4, "East"));
		b5.addRoad(new Road(b5, b4, "North"));
		b5.addRoad(new Road(b5, a5, "West"));
		b6.addRoad(new Road(b6, b5, "North"));
		b6.addRoad(new Road(b6, c6, "East"));
		b6.addRoad(new Road(b6, a6, "West"));
		b7.addRoad(new Road(b7, b6, "North"));
		b7.addRoad(new Road(b7, a7, "West"));

		c1.addRoad(new Road(c1, c2, "South"));
		c1.addRoad(new Road(c1, b1, "West"));
		c2.addRoad(new Road(c2, d2, "East"));
		c2.addRoad(new Road(c2, c3, "South"));
		c2.addRoad(new Road(c2, b2, "West"));
		c3.addRoad(new Road(c3, c4, "South"));
		c3.addRoad(new Road(c3, b3, "West"));
		c4.addRoad(new Road(c4, d4, "East"));
		c4.addRoad(new Road(c4, c5, "South"));
		c5.addRoad(new Road(c5, c6, "South"));
		c5.addRoad(new Road(c5, b5, "West"));
		c6.addRoad(new Road(c6, d6, "East"));
		c6.addRoad(new Road(c6, c7, "South"));
		c6.addRoad(new Road(c6, b6, "West"));
		c7.addRoad(new Road(c7, b7, "West"));

		d1.addRoad(new Road(d1, d2, "South"));
		d1.addRoad(new Road(d1, c1, "West"));
		d2.addRoad(new Road(d2, d1, "North"));
		d2.addRoad(new Road(d2, e2, "East"));
		d2.addRoad(new Road(d2, d3, "South"));
		d2.addRoad(new Road(d2, c2, "West"));
		d3.addRoad(new Road(d3, d2, "North"));
		d3.addRoad(new Road(d3, d4, "South"));
		d3.addRoad(new Road(d3, c3, "West"));
		d4.addRoad(new Road(d4, d3, "North"));
		d4.addRoad(new Road(d4, e4, "East"));
		d4.addRoad(new Road(d4, d5, "South"));
		d5.addRoad(new Road(d5, d4, "North"));
		d5.addRoad(new Road(d5, d6, "South"));
		d5.addRoad(new Road(d5, c5, "West"));
		d6.addRoad(new Road(d6, d5, "North"));
		d6.addRoad(new Road(d6, e6, "East"));
		d6.addRoad(new Road(d6, d7, "South"));
		d6.addRoad(new Road(d6, c6, "West"));
		d7.addRoad(new Road(d7, d6, "North"));
		d7.addRoad(new Road(d7, c7, "West"));

		e1.addRoad(new Road(e1, e2, "South"));
		e1.addRoad(new Road(e1, d1, "West"));
		e2.addRoad(new Road(e2, f2, "East"));
		e2.addRoad(new Road(e2, e3, "South"));
		e2.addRoad(new Road(e2, d2, "West"));
		e3.addRoad(new Road(e3, e4, "South"));
		e3.addRoad(new Road(e3, d3, "West"));
		e4.addRoad(new Road(e4, f4, "East"));
		e4.addRoad(new Road(e4, e5, "South"));
		e5.addRoad(new Road(e5, e6, "South"));
		e5.addRoad(new Road(e5, d5, "West"));
		e6.addRoad(new Road(e6, f6, "East"));
		e6.addRoad(new Road(e6, e7, "South"));
		e6.addRoad(new Road(e6, d6, "West"));
		e7.addRoad(new Road(e7, d7, "West"));

		f1.addRoad(new Road(f1, e1, "West"));
		f2.addRoad(new Road(f2, f1, "North"));
		f2.addRoad(new Road(f2, g2, "East"));
		f2.addRoad(new Road(f2, e2, "West"));
		f3.addRoad(new Road(f3, f2, "North"));
		f3.addRoad(new Road(f3, e3, "West"));
		f4.addRoad(new Road(f4, f3, "North"));
		f4.addRoad(new Road(f4, g4, "East"));
		f5.addRoad(new Road(f5, f4, "North"));
		f5.addRoad(new Road(f5, e5, "West"));
		f6.addRoad(new Road(f6, f5, "North"));
		f6.addRoad(new Road(f6, g6, "East"));
		f6.addRoad(new Road(f6, e6, "West"));
		f7.addRoad(new Road(f7, f6, "North"));
		f7.addRoad(new Road(f7, e7, "West"));

		g1.addRoad(new Road(g1, g2, "South"));
		g1.addRoad(new Road(g1, f1, "West"));
		g2.addRoad(new Road(g2, g3, "South"));
		g2.addRoad(new Road(g2, f2, "West"));
		g3.addRoad(new Road(g3, g4, "South"));
		g3.addRoad(new Road(g3, f3, "West"));
		g4.addRoad(new Road(g4, g5, "South"));
		g5.addRoad(new Road(g5, g6, "South"));
		g5.addRoad(new Road(g5, f5, "West"));
		g6.addRoad(new Road(g6, g7, "South"));
		g6.addRoad(new Road(g6, f6, "West"));
		g7.addRoad(new Road(g7, f7, "West"));

		addIntersection(a1);
		addIntersection(a2);
		addIntersection(a3);
		addIntersection(a4);
		addIntersection(a5);
		addIntersection(a6);
		addIntersection(a7);
		addIntersection(b1);
		addIntersection(b2);
		addIntersection(b3);
		addIntersection(b4);
		addIntersection(b5);
		addIntersection(b6);
		addIntersection(b7);
		addIntersection(c1);
		addIntersection(c2);
		addIntersection(c3);
		addIntersection(c4);
		addIntersection(c5);
		addIntersection(c6);
		addIntersection(c7);
		addIntersection(d1);
		addIntersection(d2);
		addIntersection(d3);
		addIntersection(d4);
		addIntersection(d5);
		addIntersection(d6);
		addIntersection(d7);
		addIntersection(e1);
		addIntersection(e2);
		addIntersection(e3);
		addIntersection(e4);
		addIntersection(e5);
		addIntersection(e6);
		addIntersection(e7);
		addIntersection(f1);
		addIntersection(f2);
		addIntersection(f3);
		addIntersection(f4);
		addIntersection(f5);
		addIntersection(f6);
		addIntersection(f7);
		addIntersection(g1);
		addIntersection(g2);
		addIntersection(g3);
		addIntersection(g4);
		addIntersection(g5);
		addIntersection(g6);
		addIntersection(g7);
	}
	
	public List<String> getClosedIntersections() {
		ArrayList<String> closed = new ArrayList<String>();
		for(Intersection i : intersections.values()) {
			if (!i.isOpen())
				closed.add(i.getName());
		}
		
		return closed;
	}
}