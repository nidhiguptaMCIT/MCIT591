import java.io.*;
import java.util.*;

/**
 *  1. Create a new class called PassengerReader that can read the file with the 
 *  data and parse information from the csv file to create a list of Passenger objects.
 */
public class PassengerReader {
	ArrayList<Passenger> passengers = new ArrayList<>();
	ArrayList<String> columns = new ArrayList<>();
	
	public PassengerReader(String filename) {
		
		try {
			Scanner in = new Scanner(new FileReader(filename));			
			
			while (in.hasNextLine()) {

				// Read column headers
				if (columns.size() == 0) {
					String nextLine = in.nextLine();
					for (String str : nextLine.split(",")) {
						columns.add(str);
					}
				} else { // Read passenger data
					passengers.add(parsePassenger(in.nextLine()));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Parses a row into a Passenger object
	 * @param row
	 * @return
	 */
	public Passenger parsePassenger(String row) { 
		boolean isName = false;
		String newRow = "";
		
		for (char c : row.toCharArray()) {
			if (c == '"') {
				isName = !isName;
			} else if (c == ',' && !isName) {
				newRow += ";;";
			} else {
				newRow += c;
			}
		}
		
		// Split into an array
		String[] newRowArr = newRow.split(";;");
		
		int id = newRowArr[0].length() == 0 ? -1 : Integer.parseInt(newRowArr[0]);
		int survived = newRowArr[1].length() == 0 ? -1 : Integer.parseInt(newRowArr[1]);
		int pclass = newRowArr[2].length() == 0 ? -1 : Integer.parseInt(newRowArr[2]);
		int age = newRowArr[5].length() == 0 ? -1 : (int) Double.parseDouble(newRowArr[5]);
		double fare = newRowArr[9].length() == 0 ? -1 : Double.parseDouble(newRowArr[9]);
		
		Passenger p = new Passenger(id,
									survived,
									pclass,
									newRowArr[3],
									newRowArr[4],
									age,
									newRowArr[8],
									fare);
		return p;
	}	
	
	public ArrayList<Passenger> getAllPassengers() {
		return passengers;
	}
}