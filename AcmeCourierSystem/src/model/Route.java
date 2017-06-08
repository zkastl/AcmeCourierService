package model;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * an object that calculates the shortest path between two intersections and
 * stores the steps to take to follow that path
 */
public class Route {

	/**
	 * the roads that compose the shortest path from the start intersection to
	 * the end intersection
	 */
	private Stack<Road> steps;
	/**
	 * the intersection the route starts at
	 */
	private Intersection start;
	/**
	 * the intersection the route ends at
	 */
	private Intersection end;
	/**
	 * the distance traveled during the route
	 */
	private int distance;
	/**
	 * the time it takes someone moving at the speed of an average courier to
	 * travel the route
	 */
	private double time;

	/**
	 * calculate the steps that compose the shortest path from start to end
	 */
	public Intersection getStart() {
		return start;
	}
	public Intersection getEnd() {
		return end;
	}
	public int getDistance() {
		return distance;
	}
	public double getTime() {
		return time;
	}
	public Route(Map map, Intersection start, Intersection end) {
		this.start =start;
		this.end = end;
		steps = new Stack<Road>();
		calculateSteps(map);
		calculateDistance();
		calculateTime();
	}
	public void calculateSteps(Map map) {
		PriorityQueue<Intersection> unvisited = new PriorityQueue<Intersection>();
		
		// sanitize open nodes to make sure they are not affected by previous paths and add them to the unvisited set
		Intersection prevFiller = new Intersection("-1", "stupid");
		for(Intersection intersection : map.getIntersections()) {
			if(intersection.isOpen()) {
				int maxLength = 0;
				for(Road road : intersection.getRoads()){
					if(maxLength < road.getLength()) {
						maxLength = road.getLength();
					}
				}
				intersection.setDistance(Integer.MAX_VALUE - maxLength); // don't accidently roll over if something goes wrong and shove huge negative numbers to the front
				intersection.setPrevious(prevFiller);
				intersection.setVisited(false);
				if( intersection.equals(start) ) {
					intersection.setDistance(0);
				}
				unvisited.add(intersection);
			}
		}
		
		// find the shortest distance from start to end
		while(!end.getVisited()) {
			Intersection current = unvisited.poll();
			if(!current.getVisited()) {
				for(Road edge : current.getRoads()) {
					if(edge.getEnd().getDistance() > current.getDistance() + edge.getLength()) {
						edge.getEnd().setDistance(current.getDistance() + edge.getLength());
						edge.getEnd().setPrevious(current);
						unvisited.add(edge.getEnd()); // help get around priority queues not resorting all the time
					}
				}
				current.setVisited(true);
			}
		}
		
		// build stack of steps to take
		Intersection node = end;
		while(!node.getPrevious().equals(prevFiller)) {
			if(!node.getPrevious().getRoads().isEmpty()) {
				for(Road road : node.getPrevious().getRoads()) {
					if(road.getEnd().equals(node)) {
						steps.push(road);								// null pointer exception
						//insert stop for for loop here
					}
				}
				node = node.getPrevious();
			}
		}
	}

	/**
	 * calculate the total distance of the route in blocks (essentially
	 * steps.size for non-weighted edges)
	 */
	public void calculateDistance() {
		distance = 0;
		for(Road step : steps) {
			distance = distance + step.getLength();
		}
	}

	/**
	 * calculate the time it takes to travel the route, distance must be
	 * calculated first (distance / averageCourierSpeed)
	 */
	public void calculateTime() {
		time = distance / Settings.averageCourierSpeed;
	}

	/**
	 * outputs an easily to follow set of instructions on how to traverse the
	 * route
	 */
	public void print() {
		String directions = "";
		int distance = 0;
		Road prevStep = steps.peek();
		
		for(Road step : steps) {
			System.out.println(step.getDirection() + " " + step.getLength() + " on " + step.getName());
			
			if(step.getDirection().compareTo(prevStep.getDirection()) == 0) {
				distance = distance + step.getLength();
			} else {
				if(distance == 0) {
					distance = prevStep.getLength();
				}
				directions = directions + "go " + prevStep.getDirection() + " " + distance + " on " + prevStep.getName() + "\n";
				distance = 0;
			}
			
			prevStep = step;
		}
		System.out.println(directions);
	}

}