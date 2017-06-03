package model;

/**
 * an object that calculates the shortest path between two intersections and
 * stores the steps to take to follow that path
 */
public class Route {

	/**
	 * the roads that compose the shortest path from the start intersection to
	 * the end intersection
	 */
	private Road[] steps;
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
	public void calculateSteps() {
		// TODO - implement Route.calculateSteps
		throw new UnsupportedOperationException();
	}

	/**
	 * calculate the total distance of the route in blocks (essentially
	 * steps.size)
	 */
	public void calculateDistance() {
		// TODO - implement Route.calculateDistance
		throw new UnsupportedOperationException();
	}

	/**
	 * calculate the time it takes to travel the route, distance must be
	 * calculated first (distance / averageCourierSpeed)
	 */
	public void calculateTime() {
		// TODO - implement Route.calculateTime
		throw new UnsupportedOperationException();
	}

	/**
	 * outputs an easily to follow set of instructions on how to traverse the
	 * route
	 */
	public void print() {
		// TODO - implement Route.print
		throw new UnsupportedOperationException();
	}

}