import java.util.*;

/**
 *  2. Create a DataAnalysis class that contains an ArrayList of Passenger objects as an instance variable.
 *     And create two methods: getAlivePassengers and getDeceasedPassengers that return a list of alive and 
 *     deceased passengers.
 */
public class DataAnalysis {
	
	ArrayList<Passenger> passengers;
		
	public DataAnalysis(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}
	
	/**
	 * Returns an ArrayList of all alive passengers
	 * @return
	 */
	public ArrayList<Passenger> getAlivePassengers() {
		ArrayList<Passenger> alive = new ArrayList<>();
		
		for (Passenger p : passengers) {
			if (p.isAlive()) {
				alive.add(p);
			}
		}
		return alive;		
	}
	
	/**
	 * Returns an ArrayList of all deceased passengers
	 * @return
	 */
	public ArrayList<Passenger> getDeceasedPassengers() {
		ArrayList<Passenger> dead = new ArrayList<>();
		
		for (Passenger p : passengers) {
			if (!p.isAlive()) {
				dead.add(p);
			}
		}
		return dead;		
	}
	
}