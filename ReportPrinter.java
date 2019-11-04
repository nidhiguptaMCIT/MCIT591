import java.io.*;
import java.util.*;

public class ReportPrinter {
	DataAnalysis analyzer;
	
	/**
	 * Given an ArrayList of passengers, this method will print out
	 * the fares in the following format:
	 * <Name>, <Fare>
	 */
	public void printFares(ArrayList<Passenger> passengers, String filename,
						   DataAnalysis analyzer) {
		this.analyzer = analyzer;
		try {
			FileWriter fw = new FileWriter(filename);
			PrintWriter pw = new PrintWriter(fw);
			
			for (Passenger p : passengers) {
				if (p.getFare() == -1) {
					continue;
				} else {
					pw.println(p.getFare());
					pw.flush();
				}
			}
			pw.close();
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will create a report containing all of the alive passengers
	 */
	public void createAlivePassengersReport(String filename) {
		try {
			FileWriter fw = new FileWriter(filename);
			PrintWriter pw = new PrintWriter(fw);
			ArrayList<Passenger> alive = analyzer.getAlivePassengers();
			
			for (Passenger p : alive) {
				pw.println(p);
				pw.flush();
			}
			pw.close();
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will create a report containing all of the deceased
	 * passengers
	 */
	public void createDeceasedPassengersReport(String filename) {
		try {
			FileWriter fw = new FileWriter(filename);
			PrintWriter pw = new PrintWriter(fw);
			ArrayList<Passenger> dead = analyzer.getDeceasedPassengers();
			
			for (Passenger p : dead) {
				pw.println(p);
				pw.flush();
			}
			pw.close();
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
