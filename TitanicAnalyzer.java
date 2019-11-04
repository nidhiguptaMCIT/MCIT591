import java.io.*;
import java.util.*;

/**
 *  3. Build all the necessary objects (PassengerReader, DataAnalysis) in a Main class that reads in the file and calls the methods to create ArrayLists of all the passengers, all the alive passengers and all the deceased passengers.
 *  4. Find the average and median ages of survivors and the average and median ages of people who didn't survive.
 *  5. Find the average fare for 1st class, 2nd class, and 3rd class passengers and the overall average fare.
 */
public class TitanicAnalyzer {
	
	/**
	 * Gets the average age of passengers
	 * @param passengers
	 * @return
	 */
	public static double findAverageAge(ArrayList<Passenger> passengers) {
		int count = 0;
		double sum = 0;
		
		for (Passenger p : passengers) {
			if (p.getAge() == -1) {
				continue;
			} else {
				sum += p.getAge();
				count++;
			}
		}		
		return sum / count;
	}
	
	/**
	 * Calculates the median age of a given set of passengers
	 * @param passengers
	 * @return
	 */
	public static double findMedianAge(ArrayList<Passenger> passengers) {
		int count = 0;
		double sum = 0;
		
		ArrayList<Integer> ages = new ArrayList<>();
		
		// Create clean array list
		for (Passenger p : passengers) {
			if (p.getAge() != -1) {
				ages.add(p.getAge());
			}
		}
		
		Collections.sort(ages);
		
		if (ages.size() % 2 == 1) {
			return ages.get(ages.size() / 2);
		} else {
			return (ages.get(ages.size() / 2) + ages.get(ages.size() / 2 - 1)) / 2.0;
		}
	}
	
	/**
	 * Returns the average fare paid by a specific class
	 * 
	 * @param pclass Make this -1 if you'd like an overall result
	 * @param passengers
	 * @return
	 */
	public static double findAverageFare(int pclass, ArrayList<Passenger> passengers) {
		int count = 0;
		double sum = 0;
		
		if (pclass == -1) {
			for (Passenger p : passengers) {
				if (p.getFare() == -1) {
					continue;
				} else {
					count++;
					sum += p.getFare();
				}
			}
		} else {
			for (Passenger p : passengers) {
				if (p.getpClass() == pclass) {
					count++;
					sum += p.getFare();
				}
			}
		}
		return sum / count;		
	}
	
	/**
	 * Given a file with all fares, this method will return a HashMap containing
	 * the cheapest fare, the median fare, and the average fare in the following format:
	 *  "Cheapest" 	=> 	"1.07, 8 passengers"
	 *  "Average" 	=>	"31.09, 0 passengers"
	 *  "Median"	=>	"28.00, 30 passengers"
	 *  
	 * @param passengers
	 * @return
	 */
	public static HashMap<String, String> analyzeFares(String filename) {
		
		// Read in the fares, store in an ArrayList
		ArrayList<Double> fares = new ArrayList<>();
		try {
		Scanner in = new Scanner(new FileReader(filename));		
		while (in.hasNextDouble()) {
			double next = in.nextDouble();
			if (next != -1) {
				fares.add(next);
			}
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Initialize variables for use in calculation
		HashMap<String, String> output = new HashMap<>();
		double sum = 0;
		int totalCount= 0;
				
		Collections.sort(fares); // Sorting will aid in all calculations being performed
		
		// Rounding by multiplying by 100, converting to int, then converting back to double / 100
		double min = round(fares.get(0), 2);  
		int minCount = fares.lastIndexOf(fares.get(0)) + 1;
		output.put("Cheapest", min + ", " + minCount + " passenger(s)");
		
		// Calculate average fare
		for (Double d : fares) {
			if (d != -1) {
				totalCount++;
				sum += d;
			}
		}		
		double average = sum / totalCount;
		int averageCount = fares.contains(average) ? fares.lastIndexOf(average) - fares.indexOf(average) + 1 : 0;
		output.put("Average", round(average, 2) + ", " + averageCount + " passenger(s)");
		
		// Calculate median
		double median = -1;
		double medianCount = 0;		
		if (fares.size() % 2 == 1) {
			median = fares.get(fares.size() / 2);
		} else {
			median = (fares.get(fares.size() / 2) + fares.get(fares.size() / 2 - 1)) / 2.0;
		}
		medianCount = fares.lastIndexOf(median) - fares.indexOf(median) + 1;
		output.put("Median",  round(median, 2) + ", " + medianCount + " passenger(s)");
		
		return output;		
	}
	
	/**
	 * Helper method to round doubles to specified number of digits
	 * 
	 * @param d The decimal to be rounded
	 * @param decimals The number of digits
	 * @return A rounded version of the decimal
	 */
	public static double round(double d, int decimals) {
		int power = (int) Math.pow(10, decimals);
		double dTimesPower = d * power;
		if (dTimesPower - (int) dTimesPower >= .5) {
			dTimesPower = (int) dTimesPower * 1.0 + 1;
		} else {
			dTimesPower = (int) dTimesPower * 1.0;
		}		
		return dTimesPower / power;
	}
	
	public static void main(String[] args) {
		PassengerReader pr = new PassengerReader("titanic.csv");
		DataAnalysis analyzer = new DataAnalysis(pr.getAllPassengers());
		ReportPrinter printer = new ReportPrinter();		
		ArrayList<Passenger> allPassengers = pr.getAllPassengers();
		ArrayList<Passenger> alive = analyzer.getAlivePassengers();
		ArrayList<Passenger> dead = analyzer.getDeceasedPassengers();
		
		/*
		 * Answers to Q4
		 */
		System.out.println("Average age of those alive: " + findAverageAge(alive));
		System.out.println("Average age of those deceased: " + findAverageAge(dead));
		System.out.println("Median age of those alive: " + findMedianAge(alive));
		System.out.println("Median age of those deceased: " + findMedianAge(dead));
		
		/*
		 * Answer to Q5
		 */
		System.out.println();
		System.out.println("Average fare paid by class: 1 \t " + findAverageFare(1, pr.getAllPassengers()));
		System.out.println("Average fare paid by class: 2 \t " + findAverageFare(2, pr.getAllPassengers()));
		System.out.println("Average fare paid by class: 3 \t " + findAverageFare(3, pr.getAllPassengers()));
		System.out.println("Average fare paid by class: Overall \t " + findAverageFare(-1, pr.getAllPassengers()));
		
		/*
		 * Answer to Advanced Q1
		 */
		System.out.println();
		printer.printFares(allPassengers, "fares.txt", analyzer);
		HashMap<String, String> fareAnalysis = analyzeFares("fares.txt");
		for (String str : fareAnalysis.keySet()) {
			System.out.println(str + ": " + fareAnalysis.get(str));
		}
		
		/*
		 * Answer to Advanced Q2, Q3
		 */
		printer.createAlivePassengersReport("alive_passengers.tsv");
		printer.createDeceasedPassengersReport("deceased_passengers.tsv");
	}
}
